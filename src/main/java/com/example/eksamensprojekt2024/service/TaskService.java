package com.example.eksamensprojekt2024.service;

import com.example.eksamensprojekt2024.model.Task;
import com.example.eksamensprojekt2024.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findTaskByID(int taskID) {
        return taskRepository.findTaskByID(taskID);
    }

    public void createTask(String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime, int subProjectID) {
        taskRepository.createTask(taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime, subProjectID);
    }

    public List<Task> readTasks(int subProjectID) {
        return taskRepository.readTasks(subProjectID);
    }

    public void updateTask(int taskID, String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime) {
        taskRepository.updateTask(taskID, taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime);
    }

    public void deleteTask(int taskID) {
        taskRepository.deleteTask(taskID);
    }
}