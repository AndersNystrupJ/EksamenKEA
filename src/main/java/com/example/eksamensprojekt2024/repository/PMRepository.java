package com.example.eksamensprojekt2024.repository;

import com.example.eksamensprojekt2024.model.ProjectManagement;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PMRepository {

    public String url = System.getenv("url");
    public String password = System.getenv("password");
    public String user = System.getenv("user");


    public ProjectManagement findProjectByID(int id) {
        ProjectManagement projectManagement = new ProjectManagement();
        String sql = "SELECT * FROM projects WHERE projectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                projectManagement.setProjectID(rs.getInt("projectID"));
                projectManagement.setProjectName(rs.getString("projectName"));
                projectManagement.setProjectManager(rs.getString("projectManager"));
                projectManagement.setStartDate(rs.getInt("startDate"));
                projectManagement.setEndDate(rs.getInt("endDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectManagement;
    }

    public ProjectManagement createProject(String projectName, String projectManager, int startDate, int endDate) {
        ProjectManagement projectManagement = new ProjectManagement(projectName, projectManager, startDate, endDate);

        String sqlCreateProject = "INSERT INTO projects (projectName, projectManager, startDate, endDate) VALUES(?,?,?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreateProject, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, projectManagement.getProjectName());
            preparedStatement.setString(2, projectManagement.getProjectManager());
            preparedStatement.setInt(3, projectManagement.getStartDate());
            preparedStatement.setInt(4, projectManagement.getEndDate());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int projectID = generatedKeys.getInt(1);
                projectManagement.setProjectID(projectID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return projectManagement;

    }

    public List<ProjectManagement> readProjects() {
        List<ProjectManagement> projectManagements = new ArrayList<>();
        String sqlReadProjects = "SELECT * FROM projects";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlReadProjects);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ProjectManagement projectManagement = new ProjectManagement();
                projectManagement.setProjectID(rs.getInt("projectID"));
                projectManagement.setProjectName(rs.getString("projectName"));
                projectManagement.setProjectManager(rs.getString("projectManager"));
                projectManagement.setStartDate(rs.getInt("startDate"));
                projectManagement.setEndDate(rs.getInt("endDate"));
                projectManagements.add(projectManagement);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectManagements;
    }

    public void updateProject(int projectID, String projectName, String projectManager, int startDate, int endDate) {
        String sqlUpdateProjects = "UPDATE projects SET projectName = ?, projectManager = ?, startDate = ?, endDate = ? WHERE projectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlUpdateProjects);
            statement.setString(1, projectName);
            statement.setString(2, projectManager);
            statement.setInt(3, startDate);
            statement.setInt(4, endDate);
            statement.setInt(5, projectID);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int deleteProject(int id) {
        int updatedRows = 0;
        String sqlDelete = "DELETE FROM projects WHERE projectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlDelete);
            statement.setInt(1, id);
            updatedRows = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedRows;

    }


}
