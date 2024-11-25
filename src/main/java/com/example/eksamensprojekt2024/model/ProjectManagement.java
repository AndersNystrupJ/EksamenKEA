package com.example.eksamensprojekt2024.model;

public class ProjectManagement {
    private int projectId;
    private String projectName;
    private String projectManager;
    private int startDate;
    private int endDate;

    public ProjectManagement() {

    }

    public ProjectManagement(int projectId, String projectName, String projectManager, int startDate, int endDate) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectManager = projectManager;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

}
