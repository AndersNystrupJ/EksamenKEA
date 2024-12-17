package com.example.eksamensprojekt2024.service;

import com.example.eksamensprojekt2024.model.SubProject;
import com.example.eksamensprojekt2024.repository.SubProjectRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SubProjectService {

    private final SubProjectRepository subProjectRepository;

    public SubProjectService(SubProjectRepository subProjectRepository) {
        this.subProjectRepository = subProjectRepository;
    }

    public SubProject findSubProjectByID(int subProjectID) {
        return subProjectRepository.findSubProjectByID(subProjectID);
    }

    public void createSubProject(String subProjectName, int projectID, Date startDate, Date endDate) {
        subProjectRepository.createSubProject(subProjectName, projectID, startDate, endDate);
    }

    public List<SubProject> readSubProjects(int projectID) {
        return subProjectRepository.readSubProjects(projectID);
    }


    public void updateSubProjects(int subProjectID, String subProjectName, Date startDate, Date endDate) {
        subProjectRepository.updateSubProject(subProjectID, subProjectName, startDate, endDate);
    }

    public void deleteSubProject(int subProjectID){
        subProjectRepository.deleteSubProject(subProjectID);
    }
}

