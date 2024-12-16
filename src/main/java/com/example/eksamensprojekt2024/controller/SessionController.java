package com.example.eksamensprojekt2024.controller;

import com.example.eksamensprojekt2024.model.User;
import com.example.eksamensprojekt2024.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "redirect:/login";
    }

    //REGISTER PAGE:

    @GetMapping("/register")
    public String registerUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/register";
    }

    //REGISTER SAVE USER:

    @PostMapping("/register_save")
    public String saveUser(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String role) {
        userService.createUser(username, password, role);
        return "redirect:/login";
    }

    @PostMapping("/deleteUser")
    public String deleteCurrentUser(HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "You are not logged in!");
            return "redirect:/login";
        }
        userService.deleteUser(user.getEmployeeID());
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "Your account has been successfully deleted.");
        return "redirect:/login";
    }
}
