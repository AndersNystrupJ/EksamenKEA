package com.example.eksamensprojekt2024.controller;


import com.example.eksamensprojekt2024.model.Task;
import com.example.eksamensprojekt2024.service.SubProjectService;
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

    public TaskController(TaskService taskService, UserService userService, SubProjectService subProjectService) {
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
    @PostMapping("/saveTask")
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
        List<Task> tasks = taskService.readTasks(subProjectID);
        model.addAttribute("tasks", tasks);
        model.addAttribute("subProjectID", subProjectID);
        return "readTasks";
    }

    @GetMapping("/updateTask/{taskID}")
    public String editTask(@PathVariable("taskID") int taskID, Model model) {
        Task task = taskService.findTaskByID(taskID);
        model.addAttribute("task", task);
        return "editTask";
    }
    @PostMapping("/updateTask")
    public String updateTask(@RequestParam int taskID,
                             @RequestParam String taskName,
                             @RequestParam String description,
                             @RequestParam int assignedEmployeeID,
                             @RequestParam String status,
                             @RequestParam String urgency,
                             @RequestParam int estimatedTime,
                             @RequestParam int actualTime,
                             @RequestParam int subProjectID) {
        taskService.updateTask(taskID, taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime);
        return "redirect:/tasks/readTasks/" + subProjectID;
    }

    @PostMapping("/deleteTask/{taskID}")
    public String deleteTask(@PathVariable int taskID, @RequestParam int subProjectID) {
        taskService.deleteTask(taskID);
        return "redirect:/tasks/readTasks/" + subProjectID;
    }

}
