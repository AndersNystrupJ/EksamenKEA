CREATE SCHEMA IF NOT EXISTS ProjectManagementDB;

CREATE SCHEMA IF NOT EXISTS project_manager;

DROP TABLE IF EXISTS user_profile CASCADE;
DROP TABLE IF EXISTS projects CASCADE;
DROP TABLE IF EXISTS sub_project CASCADE;
DROP TABLE IF EXISTS task CASCADE;

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
                             FOREIGN KEY (projectID) REFERENCES projects(projectID)
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
                      FOREIGN KEY (subProjectID) REFERENCES sub_project(subProjectID),
                      FOREIGN KEY (assignedEmployeeID) REFERENCES user_profile(employeeID)
);
INSERT INTO user_profile (Username, Password, Role)
VALUES
    ('manager1', 'password1', 'Manager'),
    ('manager2', 'password2', 'Manager'),
    ('employee1', 'password3', 'Employee');


INSERT INTO projects (ProjectName, projectManagerID, StartDate, EndDate)
VALUES
    ('Project Alpha', 2, '2024-01-01', '2024-06-01'),
    ('Project Beta', 1, '2024-02-01', '2024-07-01');
