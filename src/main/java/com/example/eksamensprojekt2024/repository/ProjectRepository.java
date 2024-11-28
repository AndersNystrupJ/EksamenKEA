package com.example.eksamensprojekt2024.repository;

import com.example.eksamensprojekt2024.model.Project;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {

    public String url = System.getenv("DEV_DB_URL");
    public String password = System.getenv("DEV_DB_PASSWORD");
    public String user = System.getenv("DEV_DB_USERNAME");


    public Project findProjectByID(int id) {
        Project project = new Project();
        String sql = "SELECT * FROM projects WHERE projectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                project.setProjectID(rs.getInt("projectID"));
                project.setProjectName(rs.getString("projectName"));
                project.setProjectManager(rs.getString("projectManager"));
                project.setStartDate(rs.getInt("startDate"));
                project.setEndDate(rs.getInt("endDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public Project createProject(String projectName, String projectManager, int startDate, int endDate) {
        Project project = new Project(projectName, projectManager, startDate, endDate);

        String sqlCreateProject = "INSERT INTO projects (projectName, projectManager, startDate, endDate) VALUES(?,?,?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreateProject, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getProjectManager());
            preparedStatement.setInt(3, project.getStartDate());
            preparedStatement.setInt(4, project.getEndDate());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int projectID = generatedKeys.getInt(1);
                project.setProjectID(projectID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return project;

    }

    public List<Project> readProjects() {
        List<Project> projects = new ArrayList<>();
        String sqlReadProjects = "SELECT * FROM projects";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlReadProjects);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("projectID"));
                project.setProjectName(rs.getString("projectName"));
                project.setProjectManager(rs.getString("projectManager"));
                project.setStartDate(rs.getInt("startDate"));
                project.setEndDate(rs.getInt("endDate"));
                projects.add(project);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
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
