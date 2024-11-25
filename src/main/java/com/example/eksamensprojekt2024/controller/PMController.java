package com.example.eksamensprojekt2024.controller;

import com.example.eksamensprojekt2024.model.ProjectManagement;
import com.example.eksamensprojekt2024.service.PMService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class PMController {
    private final PMService pmService;

    public PMController(PMService pmService) {
        this.pmService = pmService;
    }


    @GetMapping("/createProject")
    public String createProject(Model model) {
        ProjectManagement projectManagement = new ProjectManagement();
        model.addAttribute("Project", projectManagement);
        return "createProject";
    }

    @PostMapping("/saveProject")
    public String saveProject(@RequestParam String projectName,
                              @RequestParam String projectManager,
                              @RequestParam int startDate,
                              @RequestParam int endDate) {
        pmService.createProject(projectName, projectManager, startDate, endDate);
        return "redirect:/projects";
    }

    @GetMapping("/projects")
    public String readProjects(Model model) {
        List<ProjectManagement> projectManagement = pmService.readProjects();
        model.addAttribute("Project", projectManagement);
        return "readProjects";
    }



}
