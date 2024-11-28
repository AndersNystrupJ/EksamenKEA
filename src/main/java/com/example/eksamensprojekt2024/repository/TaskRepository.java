package com.example.eksamensprojekt2024.repository;


import com.example.eksamensprojekt2024.model.Task;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    public String url = System.getenv("DEV_DB_URL");
    public String password = System.getenv("DEV_DB_PASSWORD");
    public String user = System.getenv("DEV_DB_USERNAME");

    public Task findTaskByID(int id) {
        Task task = new Task();
        String sql = "SELECT * FROM task WHERE taskID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public Task createTask(String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime) {
        Task task = new Task();

        String sqlCreateProject = "INSERT INTO task (taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime) VALUES(?,?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreateProject, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setInt(3, task.getAssignedEmployeeID());
            preparedStatement.setString(4, task.getStatus());
            preparedStatement.setString(5, task.getUrgency());
            preparedStatement.setInt(6, task.getEstimatedTime());
            preparedStatement.setInt(7, task.getActualTime());
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

    public List<Task> readTasks() {
        List<Task> tasks = new ArrayList<>();
        String sqlReadTasks = "SELECT * FROM task";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlReadTasks);
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

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int deleteTask(int id) {
        int updatedRows = 0;
        String sqlDelete = "DELETE FROM task WHERE taskID = ?";

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


