package com.example.eksamensprojekt2024.model;

public class SubProject {
    private int subProjectID;
    private String subProjectName;
    private String subProjectManager;
    private int startDate;
    private int endDate;

    public SubProject(){

    }
    public SubProject(String subProjectName, String subProjectManager, int startDate, int endDate) {
        this.subProjectName = subProjectName;
        this.subProjectManager = subProjectManager;
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

    public String getSubProjectManager() {
        return subProjectManager;
    }

    public void setSubProjectManager(String subProjectManager) {
        this.subProjectManager = subProjectManager;
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
