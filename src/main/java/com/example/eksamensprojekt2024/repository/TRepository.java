package com.example.eksamensprojekt2024.repository;


import com.example.eksamensprojekt2024.model.ProjectManagement;
import com.example.eksamensprojekt2024.model.Tasks;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TRepository {

    public String url = System.getenv("url");
    public String password = System.getenv("password");
    public String user = System.getenv("user");

    public Tasks findTaskByID(int id) {
        Tasks tasks = new Tasks();
        String sql = "SELECT * FROM task WHERE taskID = ?";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                tasks.setTaskID(rs.getInt("taskID"));
                tasks.setTaskName(rs.getString("taskName"));
                tasks.setDescription(rs.getString("description"));
                tasks.setAssignedEmployeeID(rs.getInt("assignedEmployeeID"));
                tasks.setStatus(rs.getString("status"));
                tasks.setUrgency(rs.getString("urgency"));
                tasks.setEstimatedTime(rs.getInt("estimatedTime"));
                tasks.setActualTime(rs.getInt("actualTime"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public Tasks createTask(String taskName, String description, int assignedEmployeeID, String status, String urgency, int estimatedTime, int actualTime) {
        Tasks tasks = new Tasks();

        String sqlCreateProject = "INSERT INTO task (taskName, description, assignedEmployeeID, status, urgency, estimatedTime, actualTime) VALUES(?,?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = con.prepareStatement(sqlCreateProject, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tasks.getTaskName());
            preparedStatement.setString(2, tasks.getDescription());
            preparedStatement.setInt(3, tasks.getAssignedEmployeeID());
            preparedStatement.setString(4, tasks.getStatus());
            preparedStatement.setString(5, tasks.getUrgency());
            preparedStatement.setInt(6, tasks.getEstimatedTime());
            preparedStatement.setInt(7, tasks.getActualTime());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int taskID = generatedKeys.getInt(1);
                tasks.setTaskID(taskID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tasks;
    }

    public List<Tasks> readTasks() {
        List<Tasks> tasks = new ArrayList<>();
        String sqlReadTasks = "SELECT * FROM task";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = con.prepareStatement(sqlReadTasks);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tasks task = new Tasks();
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


