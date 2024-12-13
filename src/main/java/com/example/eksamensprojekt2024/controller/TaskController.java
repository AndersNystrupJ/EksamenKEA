package com.example.eksamensprojekt2024.controller;


import com.example.eksamensprojekt2024.model.Task;
import com.example.eksamensprojekt2024.service.TaskService;
import com.example.eksamensprojekt2024.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping
    public String index(Model model, HttpSession session) {
        model.addAttribute("taskService", taskService);
        model.addAttribute("userService", userService);
        if (session.getAttribute("user") != null) {
            model.addAttribute("userAvailable", true);
            model.addAttribute("user", session.getAttribute("user"));
        } else {
            model.addAttribute("userAvailable", false);
        }

        return "redirect:/tasks/readTasks";
    }

    @GetMapping("/createTask/{subProjectID}")
    public String createTask(@PathVariable("subProjectID") int subProjectID, Model model){
        Task task = new Task();
        task.setSubProjectID(subProjectID);
        model.addAttribute("task", task);
        model.addAttribute("subProjectID", subProjectID);
        return "createTask";
}
    @PostMapping("/saveTasks")
    public String saveTasks(@RequestParam String taskName,
                           @RequestParam String description,
                           @RequestParam int assignedEmployeeID,
                           @RequestParam String status,
                           @RequestParam String urgency,
                           @RequestParam int estimatedTime,
                           @RequestParam int actualTime,
                           @RequestParam int subProjectID){
        taskService.createTask(taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime, subProjectID);
        return "redirect:/tasks/readTasks/" + subProjectID;
    }


    @GetMapping("/readTasks/{subProjectID}")
    public String readTasks(@PathVariable("subProjectID") int subProjectID, Model model) {
        List<Task> task = taskService.readTasks(subProjectID);
        model.addAttribute("task", task);
        model.addAttribute("subProjectID", subProjectID);
        return "readTasks";
    }

    @GetMapping("/{id}/edit")
    public String editTask(@PathVariable("id") int taskID, Model model) {
        Task task = taskService.findTaskByID(taskID);
        model.addAttribute("task", task);
        return "editTask";
    }
    @PostMapping("/edit")
    public String updateTask(@RequestParam int taskID,
                                @RequestParam String taskName,
                                @RequestParam String description,
                                @RequestParam int assignedEmployeeID,
                                @RequestParam String status,
                                @RequestParam String urgency,
                                @RequestParam int estimatedTime,
                                @RequestParam int actualTime) {
        taskService.updateTask(taskID, taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime);
        return "redirect:/projects";
    }
    @PostMapping("delete/{id}")
    public String deleteTaskByID(@PathVariable int id) {
        taskService.deleteTask(id);
        return "redirect:/projects";
    }

}
