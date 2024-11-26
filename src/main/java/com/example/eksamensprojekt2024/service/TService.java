package com.example.eksamensprojekt2024.service;

import com.example.eksamensprojekt2024.model.Tasks;
import com.example.eksamensprojekt2024.repository.TRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TService {
    private final TRepository tRepository;

    public TService(TRepository tRepository) {
        this.tRepository = tRepository;
    }

    public Tasks findTaskByID(int id) {
        return tRepository.findTaskByID(id);
    }

    public void createTask(String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime) {
        tRepository.createTask(taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime);
    }

    public List<Tasks> readTasks() {
        return tRepository.readTasks();
    }

    public void updateTask(int taskID, String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime) {
        tRepository.updateTask(taskID, taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime);
    }

    public void deleteTask(int id) {
        tRepository.deleteTask(id);
    }
}