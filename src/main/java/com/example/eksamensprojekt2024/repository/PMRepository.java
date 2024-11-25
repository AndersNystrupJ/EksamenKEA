package com.example.eksamensprojekt2024.repository;

import com.example.eksamensprojekt2024.model.ProjectManagement;
import org.springframework.stereotype.Repository;

import java.sql.*;

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
                projectManagement.setProjectId(rs.getInt("projectID"));
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
        ProjectManagement projectManagement = new ProjectManagement();

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
                projectManagement.setProjectId(projectID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return projectManagement;

    }

}
