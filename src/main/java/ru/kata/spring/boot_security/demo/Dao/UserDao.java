package ru.kata.spring.boot_security.demo.Dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUser(long id);
    void removeUser(long id);
    void updateUser(long id, User user);
    void saveUser(User user);
}
