package com.istep.Spring_Security.controllers;

import com.istep.Spring_Security.models.User;
import com.istep.Spring_Security.service.RoleService;
import com.istep.Spring_Security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.security.Principal;

@Controller
public class WebController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public WebController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

//    @GetMapping("/user")
//    public String userPage(Principal principal, Model model) {
//        model.addAttribute("user", userService.getUserByName(principal.getName()));
//        return "user";
//    }

    @GetMapping("/admin/edit")
    public String editUserPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", roleService.getListRoles());
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String updateUser(@RequestParam("id") Long id,
                             @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/new")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getListRoles());
        return "new";
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        User user = userService.getUserByName(principal.getName());
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        model.addAttribute("user", user);
        return "user";
    }


}