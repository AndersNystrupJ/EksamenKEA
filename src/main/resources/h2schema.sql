CREATE SCHEMA IF NOT EXISTS ProjectManagementDB;

CREATE TABLE Bruger (
                      EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
                      Username VARCHAR(255) NOT NULL UNIQUE,
                      Password VARCHAR(255) NOT NULL,
                      Role VARCHAR(255) NOT NULL
);

-- Create Project table (references User as Project Manager)
CREATE TABLE Projects (
                          ProjectID INT PRIMARY KEY AUTO_INCREMENT,
                          ProjectName VARCHAR(255) NOT NULL,
                          ProjectManagerID INT,
                          StartDate DATE,
                          EndDate DATE,
                          EmployeeID INT,
                          FOREIGN KEY (EmployeeID) REFERENCES Bruger(EmployeeID)
);

CREATE TABLE SubProject(
                           SubProjectID INT PRIMARY KEY AUTO_INCREMENT,
                           SubProjectName VARCHAR(255) NOT NULL,
                           SubProjectManager VARCHAR(255) NOT NULL,
                           StartDate DATE,
                           EndDate DATE,
                           ProjectID INT,
                           FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID)
);

-- Create Task table (references Employee and Project)
CREATE TABLE Task (
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