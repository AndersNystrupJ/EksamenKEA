package com.example.eksamensprojekt2024.model;

import java.sql.Date;

public class Project {
    private int projectID;
    private String projectName;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private int projectManagerID;
    private int totalEstimatedTimeProject;



    public Project() {

    }

    public Project(String projectName,  java.sql.Date startDate, java.sql.Date endDate, int projectManagerID) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectManagerID = projectManagerID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getProjectManagerID() {
        return projectManagerID;
    }

    public void setProjectManagerID(int projectManagerID) {
        this.projectManagerID = projectManagerID;
    }

    public int getTotalEstimatedTimeProject() {
        return totalEstimatedTimeProject;
    }

    public void setTotalEstimatedTimeProject(int totalEstimatedTimeProject) {
        this.totalEstimatedTimeProject = totalEstimatedTimeProject;
    }
}
