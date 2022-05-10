package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        User userA = userService.findUserByName(authentication.getName());
        System.out.println("\u001B[34mADMIN: \u001B[0m" + userA);
        modelMap.addAttribute("userA", userA);
        modelMap.addAttribute("users", userService.listUsers());
        modelMap.addAttribute("roles", roleService.listRoles());
        modelMap.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping(value = "/admin/newUser")
    public String newUser(User user) {
        System.out.println("\u001B[33mPOST NEW: \u001B[0m" + user);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser (@RequestParam() long id, @ModelAttribute("userEdit") User userEdit) {
        System.out.println("\u001B[33mPOST EDIT: \u001B[0m" + id + " " + userEdit);
        userService.updateUser(id, userEdit);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/delete")
    public String deleteUser(long id) {
        System.out.println("\u001B[33mPOST DELETE: \u001B[0m" + id);
        userService.removeUser(id);
        return "redirect:/admin";
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
