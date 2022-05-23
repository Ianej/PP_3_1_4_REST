package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class RestUserController {
    private final UserService userService;
    private final RoleService roleService;

    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/admin/newUser")
    public User newUser(User user) throws Exception {
        if (userService.findUserByName(user.getName())!=null) {
            throw new Exception("Имя существует, введите пожалуйста другое имя");
        }
        User user1 = userService.addUser(user);
        return  user1;
    }

    @RequestMapping(value = "/admin/usersList")
    public List<User> getUsersList() {
        return userService.listUsers();
    }

    @RequestMapping(value = "/admin/edit")
    public User editUser (@RequestParam() long id, @ModelAttribute("userEdit") User userEdit) throws Exception {
        User user = userService.findUserByName(userEdit.getName());
        if (user!=null && user.getId() != id) {
            throw new Exception("Имя уже занято, введите пожалуйста другое имя");
        }
        return userService.updateUser(id, userEdit);
    }

    @RequestMapping(value = "/admin/delete")
    public Map<String, String> deleteUser(long id) throws Exception {

        User user = userService.findById(id).orElseThrow(() -> new Exception(
                "Нельзя удалить несуществующего пользователя с id=" + id));
        userService.removeUser(id);
        return Collections.singletonMap("id", "" + id);
    }

    @RequestMapping(value = "/admin/user")
    public User getUser(long id) throws Exception {
        User user = userService.findById(id).orElseThrow(() -> new Exception(
                "Нельзя узнать данные несуществующего пользователя с id=" + id));
        return user;
    }
}
