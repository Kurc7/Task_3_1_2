package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.List;

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

    @PostMapping("/remove/{id}")
    public String deleteUser(@PathVariable(value = "id") int id) {
        userService.removeUser(id);
        return "redirect:/admin/";
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
        return "redirect:/admin";
    }

    @GetMapping(value = {"/edit/{id}"})
    public String editNewUserGet(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, String name, String lastname, String email, String password, Model model) {
        userService.updateUser(id, name, lastname, email, password);
        return "redirect:/admin/";
    }
}