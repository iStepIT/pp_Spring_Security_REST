package com.istep.Spring_Security.controllers;

import com.istep.Spring_Security.models.Role;
import com.istep.Spring_Security.models.User;
import com.istep.Spring_Security.services.RoleService;
import com.istep.Spring_Security.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAllUsers(ModelMap model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        List<User> listOfUsers = userService.getAllUsers();
        model.addAttribute("listOfUsers", listOfUsers);
        return "users";
    }

    @GetMapping("/admin/{id}/update")
    public String editUserPage(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/new")
    public String createNewUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        Collection<Role> roles = roleService.getListRoles();
        model.addAttribute("role", roles);
        return "new";
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allRoles", roleService.getListRoles());
            return "new";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin/";
    }

}