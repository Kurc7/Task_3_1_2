package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class DBInit {
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBInit(UserRepository userRepository, UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void postConstruct() {
        User user = userRepository.findByName("admin");
        if (user == null) {
            roleService.save(new Role("ROLE_ADMIN"));
            roleService.save(new Role("ROLE_USER"));
            String[] roles = {"ROLE_ADMIN", "ROLE_USER"};
            userService.saveUser(new User("admin", "admin", "admin@admin.ru"), roles, passwordEncoder.encode("admin"));
        }
    }
}