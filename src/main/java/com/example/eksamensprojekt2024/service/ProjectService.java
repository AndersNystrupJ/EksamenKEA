package com.example.eksamensprojekt2024.service;

import com.example.eksamensprojekt2024.model.Project;
import com.example.eksamensprojekt2024.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public Project findProjectByID(int id) {
        return projectRepository.findProjectByID(id);
    }

    public void createProject(String projectName, Date startDate, Date endDate, int projectManagerID) {
        projectRepository.createProject(projectName, startDate, endDate, projectManagerID);
    }

    public List<Project> readProjects() {
        return projectRepository.readProjects();
    }

    public void updateProject(int projectID, String projectName, Date startDate, Date endDate) {
        projectRepository.updateProject(projectID, projectName, startDate, endDate);
    }

    public void deleteProject(int id) {
        projectRepository.deleteProject(id);
    }

}

