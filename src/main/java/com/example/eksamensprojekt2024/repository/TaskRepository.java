package com.example.eksamensprojekt2024.repository;


import com.example.eksamensprojekt2024.model.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    /*public String url = System.getenv("DEV_DB_URL");
    public String password = System.getenv("DEV_DB_PASSWORD");
    public String user = System.getenv("DEV_DB_USERNAME");*/
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    public Task findTaskByID(int taskID) {
        Task task = new Task();
        String sql = "SELECT * FROM task WHERE taskID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, taskID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                task.setTaskID(rs.getInt("taskID"));
                task.setTaskName(rs.getString("taskName"));
                task.setDescription(rs.getString("description"));
                task.setAssignedEmployeeID(rs.getInt("assignedEmployeeID"));
                task.setStatus(rs.getString("status"));
                task.setUrgency(rs.getString("urgency"));
                task.setEstimatedTime(rs.getInt("estimatedTime"));
                task.setActualTime(rs.getInt("actualTime"));
                task.setSubProjectID(rs.getInt("subProjectID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public Task createTask(String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime, int subProjectID) {
        Task task = new Task(taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime, subProjectID);

        String sqlCreateTask = "INSERT INTO task (taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime, subProjectID) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreateTask, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setInt(3, task.getAssignedEmployeeID());
            preparedStatement.setString(4, task.getStatus());
            preparedStatement.setString(5, task.getUrgency());
            preparedStatement.setInt(6, task.getEstimatedTime());
            preparedStatement.setInt(7, task.getActualTime());
            preparedStatement.setInt(8, task.getSubProjectID());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int taskID = generatedKeys.getInt(1);
                task.setTaskID(taskID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return task;
    }

    public List<Task> readTasks(int subProjectID) {
        List<Task> tasks = new ArrayList<>();
        String sqlReadTasks = "SELECT * FROM task WHERE subProjectID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlReadTasks);
            statement.setInt(1, subProjectID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Task task = new Task();
                task.setTaskID(rs.getInt("taskID"));
                task.setTaskName(rs.getString("taskName"));
                task.setDescription(rs.getString("description"));
                task.setAssignedEmployeeID(rs.getInt("assignedEmployeeID"));
                task.setStatus(rs.getString("status"));
                task.setUrgency(rs.getString("urgency"));
                task.setEstimatedTime(rs.getInt("estimatedTime"));
                task.setActualTime(rs.getInt("actualTime"));
                task.setSubProjectID(rs.getInt("subProjectID"));
                tasks.add(task);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void updateTask(int taskID, String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime) {
        String sqlUpdateTasks = "UPDATE task SET taskName = ?, description = ?, assignedEmployeeID = ?, status = ?, urgency = ?, estimatedTime = ?, actualTime = ? WHERE taskID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlUpdateTasks);
            statement.setString(1, taskName);
            statement.setString(2, description);
            statement.setInt(3, assignedEmployeeID);
            statement.setString(4, status);
            statement.setString(5, urgency);
            statement.setInt(6, estimatedTime);
            statement.setInt(7, actualTime);
            statement.setInt(8, taskID);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int deleteTask(int taskID) {
        int updatedRows = 0;
        String sqlDelete = "DELETE FROM task WHERE taskID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlDelete);
            statement.setInt(1, taskID);
            updatedRows = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRows;
    }

}


