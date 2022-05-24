package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return (List<User>) userDao.findAll();
    }
    @Transactional
    @Override
    public User addUser(User user) {
        System.out.println("\u001B[37mADD: \u001B[0m" + user);
        return userDao.save(user);
    }
    @Transactional
    @Override
    public User updateUser(long id, User user) {
        user.setId(id);
        return userDao.save(user);
    }
    @Transactional
    @Override
    public void removeUser(long id) {
        userDao.delete(getUser(id));
    }
    @Transactional
    @Override
    public User getUser(long id) {
        return userDao.findById(id).get();
    }
    @Transactional
    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Transactional
    @Override
    public List<Role> getRolesByUser(long id) {
        return getUser(id).getRoles();
    }
    @Transactional
    @Override
    public Optional<User> findById(long id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username -> " + username);
        User user = userDao.findUserByName(username);
        if(user == null) {
            throw new UsernameNotFoundException("Неизвестный пользователь: " + username);
        }
        List<String> str = new ArrayList<>();
        for (Role role : getRolesByUser(user.getId())) {
            System.out.println(role);
            str.add(role.getRoleName());
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(str.toArray(new String[0]))
                .build();;

        return userDetails;
    }
}
