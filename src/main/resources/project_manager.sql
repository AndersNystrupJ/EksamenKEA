CREATE SCHEMA IF NOT EXISTS project_manager;
USE project_manager;

CREATE TABLE user_profile (
                              employeeID INT PRIMARY KEY AUTO_INCREMENT,
                              username VARCHAR(255) NOT NULL UNIQUE,
                              password VARCHAR(255) NOT NULL,
                              role VARCHAR(255) NOT NULL
);

CREATE TABLE projects (
                          projectID INT PRIMARY KEY AUTO_INCREMENT,
                          projectName VARCHAR(255) NOT NULL,
                          projectManagerID INT,
                          startDate DATE,
                          endDate DATE
);

CREATE TABLE sub_project (
                             subProjectID INT PRIMARY KEY AUTO_INCREMENT,
                             subProjectName VARCHAR(255) NOT NULL,
                             startDate DATE,
                             endDate DATE,
                             projectID INT,
                             FOREIGN KEY (projectID) REFERENCES projects(projectID) ON DELETE CASCADE
);

CREATE TABLE task (
                      taskID INT PRIMARY KEY AUTO_INCREMENT,
                      taskName VARCHAR(255) NOT NULL,
                      description TEXT,
                      assignedEmployeeID INT,
                      status VARCHAR(255) NOT NULL,
                      urgency VARCHAR(255) NOT NULL,
                      estimatedTime INT,
                      actualTime INT,
                      subProjectID INT,
                      FOREIGN KEY (subProjectID) REFERENCES sub_project(subProjectID) ON DELETE CASCADE,
                      FOREIGN KEY (assignedEmployeeID) REFERENCES user_profile(employeeID) ON DELETE SET NULL
);