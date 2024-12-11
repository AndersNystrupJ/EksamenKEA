package com.example.eksamensprojekt2024.controller;

import com.example.eksamensprojekt2024.model.SubProject;
import com.example.eksamensprojekt2024.service.SubProjectService;
import com.example.eksamensprojekt2024.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subProjects")  // Prefix all URLs in this controller with '/subProjects'
public class SubProjectController {
    private final SubProjectService subProjectService;
    private final UserService userService;

    public SubProjectController(SubProjectService subProjectService, UserService userService) {
        this.subProjectService = subProjectService;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model, HttpSession session) {
        model.addAttribute("subProjectService", subProjectService);
        model.addAttribute("userService", userService);
        if (session.getAttribute("user") != null) {
            model.addAttribute("userAvailable", true);
            model.addAttribute("user", session.getAttribute("user"));
        } else {
            model.addAttribute("userAvailable", false);
        }
        return "redirect:/subProjects";  // or to another view like "readSubProjects"
    }

    @GetMapping("/create")  // Updated URL mapping for the create page
    public String createSubProject(Model model) {
        SubProject subProject = new SubProject();
        model.addAttribute("SubProject", subProject);
        return "createSubProject";
    }

    @PostMapping("/save")  // Updated URL mapping for saving the subproject
    public String saveSubProject(@RequestParam String subProjectName,
                                 @RequestParam int projectID,
                                 @RequestParam int startDate,
                                 @RequestParam int enddate) {
        subProjectService.createSubProject(subProjectName, projectID, startDate, enddate);
        return "redirect:/subProjects";
    }

    @GetMapping("/list")  // Updated URL mapping for the list of subprojects
    public String readSubProjects(Model model) {
        List<SubProject> subProjects = subProjectService.readSubProjects();
        model.addAttribute("subProjectManagement", subProjects);
        return "readSubProjects";
    }

    @GetMapping("/{id}/edit")  // Edit subproject by ID
    public String editSubProject(@PathVariable("id") int subProjectID, Model model) {
        SubProject subProject = subProjectService.findSubProjectByID(subProjectID);
        model.addAttribute("project", subProject);
        return "edit";
    }

    @PostMapping("/edit")  // Edit and update subproject
    public String updateSubProject(@RequestParam int subProjectID,
                                   @RequestParam String subProjectName,
                                   @RequestParam String subProjectManager,
                                   @RequestParam int startDate,
                                   @RequestParam int endDate) {
        subProjectService.updateSubProjects(subProjectID, subProjectName, subProjectManager, startDate, endDate);
        return "redirect:/subProjects";
    }

    @PostMapping("delete/{id}")  // Delete subproject by ID
    public String deleteSubProjectByID(@PathVariable int id) {
        subProjectService.deleteSubProject(id);
        return "redirect:/subProjects";
    }
}

