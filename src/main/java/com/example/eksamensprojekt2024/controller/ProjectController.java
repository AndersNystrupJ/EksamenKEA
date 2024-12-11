package com.example.eksamensprojekt2024.controller;

import com.example.eksamensprojekt2024.model.Project;
import com.example.eksamensprojekt2024.model.User;
import com.example.eksamensprojekt2024.service.ProjectService;
import com.example.eksamensprojekt2024.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
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
    public String createProject(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            redirectAttributes.addFlashAttribute("error", "You are not logged in!");
            return "redirect:/login";
        }
        String role = user.getRole();
        if(!"ROLE_ADMIN".equalsIgnoreCase(role) && !"ROLE_PROJECT_MANAGER".equalsIgnoreCase(role)){
            redirectAttributes.addFlashAttribute("error", "You do not have permission to access this resource!");
            return "redirect:/projects/readProjects";
        }
        Project project = new Project();
        model.addAttribute("project", project);
        model.addAttribute("user", user);
        return "createProject";
    }

    @PostMapping("/save")
    public String saveProject(@RequestParam String projectName,
                              @RequestParam Date startDate,
                              @RequestParam Date endDate,
                              @RequestParam int projectManagerID) {
        projectService.createProject(projectName, startDate, endDate, projectManagerID);
        return "redirect:/projects/readProjects";
    }

    @GetMapping("/readProjects")
    public String readProjects(Model model) {
        List<Project> projects = projectService.readProjects();
        model.addAttribute("projects", projects);
        return "readProjects";
    }

    @GetMapping("/{id}/edit")
    public String editProject(@PathVariable("id") int projectID, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            redirectAttributes.addFlashAttribute("error", "You are not logged in!");
            return "redirect:/login";
        }
        String role = user.getRole();
        if(!"ROLE_ADMIN".equalsIgnoreCase(role) && !"ROLE_MANAGER".equalsIgnoreCase(role)){
            redirectAttributes.addFlashAttribute("error", "You do not have permission to access this resource!");
            return "redirect:/projects/readProjects";
        }
        Project project = projectService.findProjectByID(projectID);
        model.addAttribute("project", project);
        return "editProject";
    }

    @PostMapping("/edit")
    public String updateProject(@RequestParam int projectID,
                                @RequestParam String projectName,
                                @RequestParam Date startDate,
                                @RequestParam Date endDate) {
        projectService.updateProject(projectID, projectName, startDate, endDate);
        return "redirect:/projects";
    }

    @PostMapping("delete/{id}")
    public String deleteProjectByID(@PathVariable int id, RedirectAttributes redirectAttributes, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null){
            redirectAttributes.addFlashAttribute("error", "You are not logged in!");
            return "redirect:/login";
        }
        String role = user.getRole();
        if(!"ROLE_ADMIN".equalsIgnoreCase(role) && !"ROLE_MANAGER".equalsIgnoreCase(role)){
            redirectAttributes.addFlashAttribute("error", "You do not have permission to access this resource!");
            return "redirect:/projects/readProjects";
        }
        projectService.deleteProject(id);
        return "redirect:/projects/readProjects";
    }


}
