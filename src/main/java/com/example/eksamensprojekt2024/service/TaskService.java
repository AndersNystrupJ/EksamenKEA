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

    public Task findTaskByID(int id) {
        return taskRepository.findTaskByID(id);
    }

    public void createTask(String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime) {
        taskRepository.createTask(taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime);
    }

    public List<Task> readTasks() {
        return taskRepository.readTasks();
    }

    public void updateTask(int taskID, String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime) {
        taskRepository.updateTask(taskID, taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime);
    }

    public void deleteTask(int id) {
        taskRepository.deleteTask(id);
    }
}