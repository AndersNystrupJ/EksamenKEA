CREATE SCHEMA IF NOT EXISTS ProjectManagementDB;

DROP TABLE IF EXISTS Bruger CASCADE;
DROP TABLE IF EXISTS projects CASCADE;
DROP TABLE IF EXISTS SubProject CASCADE;
DROP TABLE IF EXISTS Task CASCADE;
CREATE TABLE IF NOT EXISTS Bruger (
                      EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
                      Username VARCHAR(255) NOT NULL UNIQUE,
                      Password VARCHAR(255) NOT NULL,
                      Role VARCHAR(255) NOT NULL
);

-- Create Project table (references User as Project Manager)
CREATE TABLE IF NOT EXISTS Projects (
                          ProjectID INT PRIMARY KEY AUTO_INCREMENT,
                          ProjectName VARCHAR(255) NOT NULL,
                          ProjectManager varchar(255),
                          StartDate DATE,
                          EndDate DATE,
                          EmployeeID INT,
                          FOREIGN KEY (EmployeeID) REFERENCES Bruger(EmployeeID)
);

CREATE TABLE IF NOT EXISTS SubProject(
                           SubProjectID INT PRIMARY KEY AUTO_INCREMENT,
                           SubProjectName VARCHAR(255) NOT NULL,
                           SubProjectManager VARCHAR(255) NOT NULL,
                           StartDate DATE,
                           EndDate DATE,
                           ProjectID INT,
                           FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID)
);

-- Create Task table (references Employee and Project)
CREATE TABLE IF NOT EXISTS Task (
                      TaskID INT PRIMARY KEY AUTO_INCREMENT,
                      TaskName VARCHAR(255) NOT NULL,
                      Description TEXT,
                      ProjectID INT,
                      AssignedEmployeeID INT,
                      StartDate DATE,
                      EndDate DATE,
                      Status VARCHAR(255) NOT NULL,
                      Urgency VARCHAR(255) NOT NULL,
                      EstimatedTime INT,
                      ActualTime INT,
                      SubProjectID INT,
                      FOREIGN KEY (SubProjectID) REFERENCES SubProject(SubProjectID)
);


INSERT INTO Bruger (Username, Password, Role)
VALUES
    ('manager1', 'password1', 'Manager'),
    ('manager2', 'password2', 'Manager'),
    ('employee1', 'password3', 'Employee');


INSERT INTO Projects (ProjectName, ProjectManager, StartDate, EndDate, EmployeeID)
VALUES
    ('Project Alpha', 'Test manager', '2024-01-01', '2024-06-01', 1),
    ('Project Beta', 'Test manager 2', '2024-02-01', '2024-07-01', 2);
