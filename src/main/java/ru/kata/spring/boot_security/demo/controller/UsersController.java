package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "show";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }
}