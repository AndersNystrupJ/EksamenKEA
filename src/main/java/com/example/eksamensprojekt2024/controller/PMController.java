package com.example.eksamensprojekt2024.controller;

import com.example.eksamensprojekt2024.model.ProjectManagement;
import com.example.eksamensprojekt2024.service.PMService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("projectManagement", projectManagement);
        return "readProjects";
    }

    @GetMapping("/{id}/edit")
    public String editProject(@PathVariable("id") int projectID, Model model) {
        ProjectManagement projectManagement = pmService.findProjectByID(projectID);
        model.addAttribute("project", projectManagement);
        return "edit";
    }

    @PostMapping("/edit")
    public String updateProject(@RequestParam int projectID,
                                @RequestParam String projectName,
                                @RequestParam String projectManager,
                                @RequestParam int startDate,
                                @RequestParam int endDate) {
        pmService.updateProject(projectID, projectName, projectManager, startDate, endDate);
        return "redirect:/projects";
    }

    @PostMapping("delete/{id}")
    public String deleteProjectByID(@PathVariable int id) {
        pmService.deleteProject(id);
        return "redirect:/projects";
    }



}
