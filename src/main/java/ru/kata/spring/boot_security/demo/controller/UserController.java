package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping(value = "/admin")
    public String printAdminPage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userA = userService.findUserByName(authentication.getName());
        System.out.println("\u001B[34mADMIN: \u001B[0m" + userA);
        modelMap.addAttribute("userA", userA);
        modelMap.addAttribute("roles", roleService.listRoles());
        return "admin";
    }

    @GetMapping(value = "/user")
    public String printUser(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(authentication.getName());
        System.out.println("\u001B[34mUSER: \u001B[0m" + user);
        modelMap.addAttribute("userA", user);
        return "user";
    }
}
