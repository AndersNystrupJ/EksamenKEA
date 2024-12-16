package com.example.eksamensprojekt2024.repository;

import com.example.eksamensprojekt2024.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "src/main/resources/h2schema.sql")
@ActiveProfiles("test")
@SpringBootTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;



    @Test
    void findProjectByID() {

    }

    /*@Test
    void createProject() {
        Date startDate = Date.valueOf("2024-12-20");
        Date endDate = Date.valueOf("2024-12-21");
        projectRepository.createProject("Test", startDate, endDate, 1);
        int expectedResult = 3;
        int actualResult = projectRepository.readProjects().size();
        assertEquals(expectedResult, actualResult);
    }*/

    @Test
    void readProjects() {
        int expectedResult = 2;
        int actualResult = projectRepository.readProjects().size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void updateProject() {
    }

    /*//@Test
    void deleteProject() {
        projectRepository.findProjectByID(1);
        int expectedResult = 1;
        int actualResult = projectRepository.readProjects().size();
        assertEquals(expectedResult, actualResult);
    }*/
}