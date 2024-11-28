package com.example.eksamensprojekt2024.controller;

import com.example.eksamensprojekt2024.model.Project;
import com.example.eksamensprojekt2024.model.User;
import com.example.eksamensprojekt2024.service.ProjectService;
import com.example.eksamensprojekt2024.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    //HTTP-SESSION:

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("projectService", projectService);
        model.addAttribute("userService", userService);
        if (session.getAttribute("user") != null) {
            model.addAttribute("userAvailable", true);
            model.addAttribute("user", session.getAttribute("user"));
        } else {
            model.addAttribute("userAvailable", false);
        }
        return "redirect:/projects";
    }


    @GetMapping("/createProject")
    public String createProject(Model model) {
        Project project = new Project();
        model.addAttribute("Project", project);
        return "createProject";
    }

    @PostMapping("/saveProject")
    public String saveProject(@RequestParam String projectName,
                              @RequestParam String projectManager,
                              @RequestParam int startDate,
                              @RequestParam int endDate) {
        projectService.createProject(projectName, projectManager, startDate, endDate);
        return "redirect:/projects";
    }

    @GetMapping("/projects")
    public String readProjects(Model model) {
        List<Project> project = projectService.readProjects();
        model.addAttribute("projectManagement", project);
        return "readProjects";
    }

    @GetMapping("/{id}/edit")
    public String editProject(@PathVariable("id") int projectID, Model model) {
        Project project = projectService.findProjectByID(projectID);
        model.addAttribute("project", project);
        return "edit";
    }

    @PostMapping("/edit")
    public String updateProject(@RequestParam int projectID,
                                @RequestParam String projectName,
                                @RequestParam String projectManager,
                                @RequestParam int startDate,
                                @RequestParam int endDate) {
        projectService.updateProject(projectID, projectName, projectManager, startDate, endDate);
        return "redirect:/projects";
    }

    @PostMapping("delete/{id}")
    public String deleteProjectByID(@PathVariable int id) {
        projectService.deleteProject(id);
        return "redirect:/projects";
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

}
