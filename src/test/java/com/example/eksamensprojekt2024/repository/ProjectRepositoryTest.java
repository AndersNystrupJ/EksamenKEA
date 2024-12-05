package com.example.eksamensprojekt2024.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "src/test/resources/h2schema.sql")
@SpringBootTest
@ActiveProfiles("test")
class ProjectRepositoryTest {

    @Test
    void findProjectByID() {
    }

    @Test
    void createProject() {
        assertEquals(1, 1);
    }

    @Test
    void readProjects() {
    }

    @Test
    void updateProject() {
    }

    @Test
    void deleteProject() {
    }
}