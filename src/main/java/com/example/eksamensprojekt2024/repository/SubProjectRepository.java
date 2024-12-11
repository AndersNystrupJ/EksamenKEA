package com.example.eksamensprojekt2024.repository;
import com.example.eksamensprojekt2024.model.SubProject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubProjectRepository {

   /* public String url = System.getenv("DEV_DB_URL");
    public String password = System.getenv("DEV_DB_PASSWORD");
    public String user = System.getenv("DEV_DB_USERNAME");*/
   @Value("${spring.datasource.url}")
   private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    public SubProject findSubProjectByID(int id) {
        SubProject subProject = new SubProject();
        String sql = "SELECT * FROM subproject WHERE projectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                subProject.setSubProjectID(rs.getInt("subProjectID"));
                subProject.setSubProjectName(rs.getString("subProjectName"));
                subProject.setProjectID(rs.getInt("projectID"));
                subProject.setStartDate(rs.getInt("startDate"));
                subProject.setEndDate(rs.getInt("endDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subProject;
    }
    public SubProject createSubProject(String subProjectName, int projectID, int startDate, int endDate) {
        SubProject subProject = new SubProject(subProjectName, projectID, startDate, endDate);

        String sqlCreateProject = "INSERT INTO subProject (subProjectName, projectID, startDate, endDate) VALUES(?,?,?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreateProject, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, subProject.getSubProjectName());
            preparedStatement.setInt(2, subProject.getSubProjectID());
            preparedStatement.setInt(3, subProject.getStartDate());
            preparedStatement.setInt(4, subProject.getEndDate());
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
    public List<SubProject> readSubProjects() {
        List<SubProject> subProjects = new ArrayList<>();
        String sqlReadProjects = "SELECT * FROM subProject";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlReadProjects);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                SubProject subProject= new SubProject();
                subProject.setSubProjectID(rs.getInt("subProjectID"));
                subProject.setSubProjectName(rs.getString("subProjectName"));
                subProject.setProjectID(rs.getInt("projectID"));
                subProject.setStartDate(rs.getInt("startDate"));
                subProject.setEndDate(rs.getInt("endDate"));
                subProjects.add(subProject);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subProjects;
    }

    /*
    public List<SubProject> readSubProjectsByID(int projectID){
        List<SubProject> subProjects = new ArrayList<>();
        String sqlReadProjectsByID = "SELECT * FROM subproject WHERE projectID = ?";
        try(Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlReadProjectsByID);

            statement.setInt(1, projectID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                SubProject subProject= new SubProject();
                subProject.setSubProjectID(rs.getInt("subProjectID"));
                subProject.setSubProjectName(rs.getString("subProjectName"));
                subProject.setProjectID(rs.getInt("projectID"));
                subProject.setStartDate(rs.getInt("startDate"));
                subProject.setEndDate(rs.getInt("endDate"));
                subProjects.add(subProject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subProjects;
    }

     */

    public void updateSubProject(int subProjectID, String subProjectName, String subProjectManager, int startDate, int endDate) {
        String sqlUpdateProjects = "UPDATE subProject SET subProjectName = ?, subProjectManager = ?, startDate = ?, endDate = ? WHERE subProjectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlUpdateProjects);
            statement.setString(1, subProjectName);
            statement.setString(2, subProjectManager);
            statement.setInt(3, startDate);
            statement.setInt(4, endDate);
            statement.setInt(5, subProjectID);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int deleteSubProject(int id) {
        int updatedRows = 0;
        String sqlDelete = "DELETE FROM subProject WHERE subProjectID = ?";

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


