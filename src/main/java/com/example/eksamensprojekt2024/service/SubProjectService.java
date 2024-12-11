package com.example.eksamensprojekt2024.service;

import com.example.eksamensprojekt2024.model.SubProject;
import com.example.eksamensprojekt2024.repository.SubProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubProjectService {

    private final SubProjectRepository subProjectRepository;

    public SubProjectService(SubProjectRepository subProjectRepository){
        this.subProjectRepository = subProjectRepository;
    }

    public SubProject findSubProjectByID(int id){
        return subProjectRepository.findSubProjectByID(id);
    }
    public void createSubProject(String subProjectName, int projectID, int startDate, int endDate){
        subProjectRepository.createSubProject(subProjectName, projectID, startDate, endDate);
    }

    public List<SubProject> readSubProjects(){
        return subProjectRepository.readSubProjects();
    }

    public List<SubProject> readSubProjectsByProjectID(int projectID){
        return subProjectRepository.readSubProjectsByID(projectID);
    }

    public void updateSubProjects(int subProjectID, String subProjectName, String subProjectManager, int startDate, int endDate){
        subProjectRepository.updateSubProject(subProjectID, subProjectName, subProjectManager, startDate, endDate);
    }

    public void deleteSubProject(int id){
        subProjectRepository.deleteSubProject(id);
    }

}
