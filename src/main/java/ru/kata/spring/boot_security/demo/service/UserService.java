package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> listUsers();
    User addUser(User user);
    User updateUser(long id, User user);
    void removeUser(long id);
    User getUser(long id);
    User findUserByName(String name);
    List<Role> getRolesByUser(long id);
    Optional<User> findById(long id);
}
