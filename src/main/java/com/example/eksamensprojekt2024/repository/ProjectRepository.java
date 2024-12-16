package com.example.eksamensprojekt2024.repository;
import com.example.eksamensprojekt2024.model.Project;
import com.example.eksamensprojekt2024.model.SubProject;
import com.example.eksamensprojekt2024.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SubProjectRepository subProjectRepository;
    @Autowired
    private TaskRepository taskRepository;

    /*public String url = System.getenv("DEV_DB_URL");
    public String password = System.getenv("DEV_DB_PASSWORD");
    public String user = System.getenv("DEV_DB_USERNAME");*/
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    public int calculateTotalEstimatedTimeForProject(int projectID) {
        List<SubProject> subProjects = subProjectRepository.readSubProjects(projectID);
        int totalEstimatedTimeProject = 0;

        for (SubProject subProject : subProjects) {
            totalEstimatedTimeProject += subProject.getTotalEstimatedTimeSubProject();
        }

        return totalEstimatedTimeProject;
    }

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
                project.setProjectManagerID(rs.getInt("projectManagerID"));
                project.setStartDate(rs.getDate("startDate"));
                project.setEndDate(rs.getDate("endDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public Project createProject(String projectName, Date startDate, Date endDate, int projectManagerID) {
        Project project = new Project(projectName, startDate, endDate, projectManagerID);
        String sqlCreateProject = "INSERT INTO projects (projectName, startDate, endDate, projectManagerID) VALUES(?,?,?,?)";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreateProject, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setDate(2, project.getStartDate());
            preparedStatement.setDate(3, project.getEndDate());
            preparedStatement.setInt(4, project.getProjectManagerID());
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
                project.setStartDate(rs.getDate("startDate"));
                project.setEndDate(rs.getDate("endDate"));
                project.setProjectManagerID(rs.getInt("projectManagerID"));
                int totalEstimatedTime = calculateTotalEstimatedTimeForProject(project.getProjectID());
                project.setTotalEstimatedTimeProject(totalEstimatedTime);
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public void updateProject(int projectID, String projectName, Date startDate, Date endDate) {
        String sqlUpdateProjects = "UPDATE projects SET projectName = ?, startDate = ?, endDate = ? WHERE projectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlUpdateProjects);
            statement.setString(1, projectName);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
            statement.setInt(4, projectID);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int deleteProject(int projectID) {
        int updatedRows = 0;
        String sqlDelete = "DELETE FROM projects WHERE projectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlDelete);
            statement.setInt(1, projectID);
            updatedRows = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRows;
    }
}
