package com.example.eksamensprojekt2024.controller;
import com.example.eksamensprojekt2024.model.SubProject;
import com.example.eksamensprojekt2024.service.ProjectService;
import com.example.eksamensprojekt2024.service.SubProjectService;
import com.example.eksamensprojekt2024.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/subProjects")
public class SubProjectController {
    private final SubProjectService subProjectService;
    private final UserService userService;
    private final ProjectService projectService;

    public SubProjectController(SubProjectService subProjectService, UserService userService, ProjectService projectService) {
        this.subProjectService = subProjectService;
        this.userService = userService;
        this.projectService = projectService;
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

        return "redirect:/subProjects/readSubProjects";
    }

    @GetMapping("/createSubProject/{projectID}")
    public String createSubProject(@PathVariable("projectID") int projectID, Model model) {
        SubProject subProject = new SubProject();
        subProject.setProjectID(projectID);
        model.addAttribute("subProject", subProject);
        model.addAttribute("projectID", projectID);
        return "createSubProject";
    }

    @PostMapping("/saveSubProject")
    public String saveSubProject(@RequestParam String subProjectName,
                                 @RequestParam int projectID,
                                 @RequestParam Date startDate,
                                 @RequestParam Date endDate) {
        subProjectService.createSubProject(subProjectName, projectID, startDate, endDate);
        return "redirect:/subProjects/readSubProjects/" + projectID;
    }

    @GetMapping("/readSubProjects/{projectID}")
    public String readSubProjects(@PathVariable("projectID") int projectID, Model model) {
        List<SubProject> subProjects = subProjectService.readSubProjects(projectID);
        model.addAttribute("subProjects", subProjects);
        model.addAttribute("projectID", projectID);
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
                                   @RequestParam Date startDate,
                                   @RequestParam Date endDate) {
        subProjectService.updateSubProjects(subProjectID, subProjectName, subProjectManager, startDate, endDate);
        return "redirect:/subProjects";
    }

    @PostMapping("delete/{id}")
    public String deleteSubProjectByID(@PathVariable int id) {
        subProjectService.deleteSubProject(id);
        return "redirect:/subProjects";
    }
}