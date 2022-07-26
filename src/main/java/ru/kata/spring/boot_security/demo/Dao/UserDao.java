package ru.kata.spring.boot_security.demo.Dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void saveUser(User user);
    void removeUser(long id);
    User getUser(long id);
    void updateUser(int id, String name, String surname, String email, String password);
    User getUserByName(String name);
}
