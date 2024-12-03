package com.example.eksamensprojekt2024.controller;


import com.example.eksamensprojekt2024.model.Task;
import com.example.eksamensprojekt2024.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {

        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String createTask(Model model){
        Task task = new Task();
        model.addAttribute("Task", task);
        return "createTask";
}
    @PostMapping("/save")
    public String saveTask(@RequestParam String taskName,
                           @RequestParam String description,
                           @RequestParam int assignedEmployeeID,
                           @RequestParam String status,
                           @RequestParam String urgency,
                           @RequestParam int estimatedTime,
                           @RequestParam int actualTime){
        taskService.createTask(taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime);
        return "redirect:/projects";
    }


    @GetMapping("/list")
    public String readTasks(Model model) {
        List<Task> tasks = taskService.readTasks();
        model.addAttribute("Tasks", tasks);
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
