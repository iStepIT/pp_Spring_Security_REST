package com.istep.Spring_Security.controllers;

import com.istep.Spring_Security.models.User;
import com.istep.Spring_Security.services.UserServiceImpl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {

        User user = userServiceImpl.getUserByName(principal.getName());
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        model.addAttribute("user", user);
        return "user";
    }
}
