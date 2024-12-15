package com.example.eksamensprojekt2024.repository;

import com.example.eksamensprojekt2024.model.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

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
}
