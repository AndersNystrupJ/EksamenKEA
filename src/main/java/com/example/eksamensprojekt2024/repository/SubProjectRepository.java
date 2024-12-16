package com.example.eksamensprojekt2024.repository;
import com.example.eksamensprojekt2024.model.SubProject;
import com.example.eksamensprojekt2024.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubProjectRepository {

    @Autowired
    private TaskRepository taskRepository;

    /* public String url = System.getenv("DEV_DB_URL");
     public String password = System.getenv("DEV_DB_PASSWORD");
     public String user = System.getenv("DEV_DB_USERNAME");*/
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;


    public int calculateTotalTimeEstimate(int subProjectID) {
        List<Task> tasks = taskRepository.readTasks(subProjectID);
        int totalEstimatedTimeSubProject = 0;

        for (Task task : tasks)
        {
            totalEstimatedTimeSubProject += task.getEstimatedTime();
        }
        return totalEstimatedTimeSubProject;
    }

    public SubProject findSubProjectByID(int subProjectID) {
        SubProject subProject = new SubProject();
        String sql = "SELECT * FROM sub_project WHERE subProjectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, subProjectID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                subProject.setSubProjectID(rs.getInt("subProjectID"));
                subProject.setSubProjectName(rs.getString("subProjectName"));
                subProject.setStartDate(rs.getDate("startDate"));
                subProject.setEndDate(rs.getDate("endDate"));
                subProject.setProjectID(rs.getInt("projectID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subProject;
    }

    public SubProject createSubProject(String subProjectName, int projectID, Date startDate, Date endDate) {
        SubProject subProject = new SubProject(subProjectName, projectID, startDate, endDate);

        String sqlCreateProject = "INSERT INTO sub_project (subProjectName, projectID, startDate, endDate) VALUES(?,?,?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreateProject, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, subProject.getSubProjectName());
            preparedStatement.setInt(2, subProject.getProjectID());
            preparedStatement.setDate(3, subProject.getStartDate());
            preparedStatement.setDate(4, subProject.getEndDate());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int subProjectID = generatedKeys.getInt(1);
                subProject.setSubProjectID(subProjectID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subProject;
    }

    public List<SubProject> readSubProjects(int projectID) {
        List<SubProject> subProjects = new ArrayList<>();
        String sqlReadSubProjects = "SELECT * FROM sub_project WHERE projectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlReadSubProjects);
            statement.setInt(1, projectID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                SubProject subProject = new SubProject();
                subProject.setSubProjectID(rs.getInt("subProjectID"));
                subProject.setSubProjectName(rs.getString("subProjectName"));
                subProject.setProjectID(rs.getInt("projectID"));
                subProject.setStartDate(rs.getDate("startDate"));
                subProject.setEndDate(rs.getDate("endDate"));
                int totalEstimatedTimeSubProject = calculateTotalTimeEstimate(subProject.getSubProjectID());
                subProject.setTotalEstimatedTimeSubProject(totalEstimatedTimeSubProject);
                subProjects.add(subProject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subProjects;
    }


    public void updateSubProject(int subProjectID, String subProjectName, Date startDate, Date endDate) {
        String sqlUpdateProjects = "UPDATE sub_project SET subProjectName = ?, startDate = ?, endDate = ? WHERE subProjectID = ?";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlUpdateProjects);
            statement.setString(1, subProjectName);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
            statement.setInt(4, subProjectID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int deleteSubProject(int subProjectID) {
        int updatedRows = 0;
        String sqlDelete = "DELETE FROM sub_project WHERE subProjectID = ?";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlDelete);
            statement.setInt(1, subProjectID);
            updatedRows = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRows;
    }
}