package com.example.eksamensprojekt2024.model;

public class
Task {
    private int taskID;
    private String taskName;
    private String description;
    private int assignedEmployeeID;
    private String status;
    private String urgency;
    private int estimatedTime;
    private int actualTime;

    public Task(){
    }

    public Task(int taskID, String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.description = description;
        this.assignedEmployeeID = assignedEmployeeID;
        this.status = status;
        this.urgency = urgency;
        this.estimatedTime = estimatedTime;
        this.actualTime = actualTime;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssignedEmployeeID() {
        return assignedEmployeeID;
    }

    public void setAssignedEmployeeID(int assignedEmployeeID) {
        this.assignedEmployeeID = assignedEmployeeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getActualTime() {
        return actualTime;
    }

    public void setActualTime(int actualTime) {
        this.actualTime = actualTime;
    }
}
