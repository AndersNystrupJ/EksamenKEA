package com.example.eksamensprojekt2024.model;

import java.sql.Date;

public class SubProject {
    private int subProjectID;
    private String subProjectName;
    private int projectID;
    private Date startDate;
    private Date endDate;
    private int totalEstimatedTimeSubProject;

    public SubProject(){
    }
    public SubProject(String subProjectName, int projectID, Date startDate, Date endDate) {
        this.subProjectName = subProjectName;
        this.projectID = projectID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getSubProjectID() {
        return subProjectID;
    }

    public void setSubProjectID(int subProjectID) {
        this.subProjectID = subProjectID;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
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

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getTotalEstimatedTimeSubProject() {
        return totalEstimatedTimeSubProject;
    }

    public void setTotalEstimatedTimeSubProject(int totalEstimatedTimeSubProject) {
        this.totalEstimatedTimeSubProject = totalEstimatedTimeSubProject;
    }
}
