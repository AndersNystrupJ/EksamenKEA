package com.example.eksamensprojekt2024.controller;

import com.example.eksamensprojekt2024.model.User;
import com.example.eksamensprojekt2024.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class SessionController {

    private UserService userService;

    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/validate_login")
    public String validateLogin(HttpSession session ,
                                @RequestParam String username,
                                @RequestParam String password) {
        User user = userService.getUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/logged_in";
        } else {
            return "login";
        }
    }

    @GetMapping("/logged_in")
    public String loggedIn(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", user);
        return "logged_in";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
