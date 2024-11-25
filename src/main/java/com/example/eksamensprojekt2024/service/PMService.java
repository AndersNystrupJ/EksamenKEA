package com.example.eksamensprojekt2024.service;

import com.example.eksamensprojekt2024.model.ProjectManagement;
import com.example.eksamensprojekt2024.repository.PMRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PMService {

    private final PMRepository pmRepository;

    public PMService(PMRepository pmRepository) {
        this.pmRepository = pmRepository;
    }


    public ProjectManagement findProjectByID(int id) {
        return pmRepository.findProjectByID(id);
    }

    public void createProject(String projectName, String projectManager, int startDate, int endDate) {
        pmRepository.createProject(projectName, projectManager, startDate, endDate);
    }

    public List<ProjectManagement> readProjects() {
        return pmRepository.readProjects();
    }

    public void updateProject(int projectID, String projectName, String projectManager, int startDate, int endDate) {
        pmRepository.updateProject(projectID, projectName, projectManager, startDate, endDate);
    }

    public void deleteProject(int id) {
        pmRepository.deleteProject(id);
    }

}

