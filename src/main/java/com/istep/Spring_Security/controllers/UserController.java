package com.istep.Spring_Security.controllers;

import com.istep.Spring_Security.models.User;
import com.istep.Spring_Security.services.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public  UserController (UserService userService){
        this.userService = userService;
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
