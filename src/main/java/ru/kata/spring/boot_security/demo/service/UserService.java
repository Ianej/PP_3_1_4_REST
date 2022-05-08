package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> listUsers();
    void addUser(User user);
    void updateUser(long id, User user);
    void removeUser(long id);
    User getUser(long id);
    User findUserByName(String name);
    List<Role> getRolesByUser(long id);
}
