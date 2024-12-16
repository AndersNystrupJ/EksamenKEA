package com.example.eksamensprojekt2024.repository;

import com.example.eksamensprojekt2024.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUser(String username, String password) {
        String query = "SELECT * FROM user_profile WHERE username = ? AND password = ?;";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        try {
            return jdbcTemplate.queryForObject(query, rowMapper, username, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void createUser(String username, String password, String role) {
        String query = "INSERT INTO user_profile(username, password, role)" + "VALUES(?, ?, ?);";
        jdbcTemplate.update(query, username, password, role);
    }

    public int deleteUser(int employeeID){
        int updatedRows = 0;
        String sqlDelete = "DELETE FROM user_profile WHERE employeeID = ?";
        try (Connection con = DriverManager.getConnection(url, user, password)){
            PreparedStatement statement = con.prepareStatement(sqlDelete);
            statement.setInt(1, employeeID);
            updatedRows = statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return updatedRows;
    }
}
