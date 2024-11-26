package com.example.eksamensprojekt2024.controller;


import com.example.eksamensprojekt2024.model.Tasks;
import com.example.eksamensprojekt2024.service.TService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class TController {

    private final TService tService;

    public TController(TService tService) {
        this.tService = tService;
    }

    @GetMapping("/createTask")
    public String createTask(Model model){
        Tasks tasks = new Tasks();
        model.addAttribute("Task", tasks);
        return "createTask";
}
    @PostMapping("/saveTask")
    public String saveTask(@RequestParam String taskName,
                           @RequestParam String description,
                           @RequestParam int assignedEmployeeID,
                           @RequestParam String status,
                           @RequestParam String urgency,
                           @RequestParam int estimatedTime,
                           @RequestParam int actualTime){
        tService.createTask(taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime);
        return "redirect:/projects";
    }


    @GetMapping("/tasks")
    public String readTasks(Model model) {
        List<Tasks> tasks = tService.readTasks();
        model.addAttribute("Tasks", tasks);
        return "readTasks";
    }

    @GetMapping("/{id}/edittask")
    public String editTask(@PathVariable("id") int taskID, Model model) {
        Tasks tasks = tService.findTaskByID(taskID);
        model.addAttribute("task", tasks);
        return "edittask";
    }
    @PostMapping("/edittask")
    public String updateTask(@RequestParam int taskID,
                                @RequestParam String taskName,
                                @RequestParam String description,
                                @RequestParam int assignedEmployeeID,
                                @RequestParam String status,
                                @RequestParam String urgency,
                                @RequestParam int estimatedTime,
                                @RequestParam int actualTime) {
        tService.updateTask(taskID, taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime);
        return "redirect:/projects";
    }
    @PostMapping("delete/{id}")
    public String deleteProjectByID(@PathVariable int id) {
        tService.deleteTask(id);
        return "redirect:/projects";
    }

}
