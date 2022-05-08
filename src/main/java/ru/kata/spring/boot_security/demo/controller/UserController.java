package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/admin")
    public String printAdminPage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(authentication.getName());
        modelMap.addAttribute("hello", "Admin " + user.getName() + ", welcome spring boot security page!");
        return "admin";
    }

    @GetMapping(value = "/admin/usersList")
    public String printUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.listUsers());
        return "usersList";
    }

    @GetMapping(value = "/admin/newUser")
    public String newUser(User user, ModelMap modelMap) {//
        modelMap.addAttribute("roles", roleService.listRoles());
        modelMap.addAttribute("user", user);
        return "newUser";
    }

    @PostMapping(value = "/admin/newUser")
    public String newUser(User user) {
        userService.addUser(user);
        return "redirect:/admin/usersList";
    }

    @GetMapping(value = "/admin/edit")
    public String editUser (@RequestParam() long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUser(id));
        modelMap.addAttribute("rolesAll",roleService.listRoles());
        return "edit";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser (@RequestParam() long id, User user) {
        userService.updateUser(id, user);
        return "redirect:/admin/usersList";
    }

    @GetMapping(value = "/admin/delete")
    public String deleteUser(long id) {
        userService.removeUser(id);
        return "redirect:/admin/usersList";
    }
    @GetMapping(value = "/user")
    public String printUser(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(authentication.getName());
        modelMap.addAttribute("hello", "User " + user.getName() + ", welcome spring boot security page!");
        modelMap.addAttribute("user", user);
        return "user";
    }
}
