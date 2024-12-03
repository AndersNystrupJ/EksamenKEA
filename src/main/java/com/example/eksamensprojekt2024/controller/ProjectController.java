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
@RequestMapping("projects")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    //HTTP-SESSION:

    @GetMapping
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


    @GetMapping("/create")
    public String createProject(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "createProject";
    }

    @PostMapping("/save")
    public String saveProject(@RequestParam String projectName,
                              @RequestParam String projectManager,
                              @RequestParam int startDate,
                              @RequestParam int endDate) {
        projectService.createProject(projectName, projectManager, startDate, endDate);
        return "redirect:/projects";
    }

    @GetMapping("/list")
    public String readProjects(Model model) {
        List<Project> projects = projectService.readProjects();
        model.addAttribute("projects", projects);
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


}
