package com.example.eksamensprojekt2024.model;

public class SubProject {
    private int subProjectID;
    private String subProjectName;
    private int startDate;
    private int endDate;
    private int projectID;


    public SubProject(){

    }
    public SubProject(String subProjectName, int projectID, int startDate, int endDate) {
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

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
