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

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return (List<User>) userDao.findAll();
    }
    @Transactional
    @Override
    public void addUser(User user) {
        System.out.println(user);
        userDao.save(user);
        System.out.println("findUserByName(user.getName()) -> " + findUserByName(user.getName()));
    }
    @Transactional
    @Override
    public void updateUser(long id, User user) {
        user.setId(id);
        userDao.save(user);
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
