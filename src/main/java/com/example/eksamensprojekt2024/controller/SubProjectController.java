package com.example.eksamensprojekt2024.controller;

import com.example.eksamensprojekt2024.model.SubProject;
import com.example.eksamensprojekt2024.model.User;
import com.example.eksamensprojekt2024.service.SubProjectService;
import com.example.eksamensprojekt2024.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class SubProjectController {
    private final SubProjectService subProjectService;
    private final UserService userService;

    public SubProjectController(SubProjectService subProjectService, UserService userService){
        this.subProjectService = subProjectService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("subProjectService", subProjectService);
        model.addAttribute("userService", userService);
        if (session.getAttribute("user") != null) {
            model.addAttribute("userAvailable", true);
            model.addAttribute("user", session.getAttribute("user"));
        } else {
            model.addAttribute("userAvailable", false);
        }
        return "redirect:/subProjects";
    }
    @GetMapping("/createSubProject")
    public String createSubProject(Model model){
        SubProject subProject = new SubProject();
        model.addAttribute("SubProject", subProject);
        return "createSubProject";
    }

    @PostMapping("/saveSubProject")
    public String saveSubProject(@RequestParam String subProjectName,
                                 @RequestParam String subProjectManager,
                                 @RequestParam int startDate,
                                 @RequestParam int enddate){
        subProjectService.createSubProject(subProjectName, subProjectManager, startDate, enddate);
        return "redirect:/subProjects";
    }

    @GetMapping("/subProjects")
    public String readSubProjects(Model model){
        List<SubProject> subProjects = subProjectService.readSubProjects();
        model.addAttribute("subProjectManagement", subProjects);
        return "readSubProjects";
    }

    @GetMapping("/{id}/edit")
    public String editSubProject(@PathVariable("id") int subProjectID, Model model) {
        SubProject subProject = subProjectService.findSubProjectByID(subProjectID);
        model.addAttribute("project", subProject);
        return "edit";
    }

    @PostMapping("/edit")
    public String updateSubProject(@RequestParam int subProjectID,
                                @RequestParam String subProjectName,
                                @RequestParam String subProjectManager,
                                @RequestParam int startDate,
                                @RequestParam int endDate) {
        subProjectService.updateSubProjects(subProjectID, subProjectName, subProjectManager, startDate, endDate);
        return "redirect:/subProjects";
    }

    @PostMapping("delete/{id}")
    public String deleteSubProjectByID(@PathVariable int id) {
        subProjectService.deleteSubProject(id);
        return "redirect:/subProjects";
    }
    /* Ved ikke om det også skal med her.
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
    }*/

}
