package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService,RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value = {"/new"})
    public String newUser(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/add")
    public String addUser (@ModelAttribute("user") User user,
                           @RequestParam(value = "rolesList") String [] roles,
                           @ModelAttribute("password") String password) {
        userService.saveUser(user, roles, password);
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String  edit (Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/update/{id}")
    public String update (@ModelAttribute("user") User user, @PathVariable("id") Long id,
                          @RequestParam(value = "rolesList") String [] roles,
                          @ModelAttribute("pass") String password) {
        userService.updateUser( id,  user.getName(),  user.getLastname(),  user.getEmail(),  password,  roles);
        return "redirect:/admin/";
    }

    @PostMapping("/remove/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin/";
    }
}