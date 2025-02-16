-- Step 1: Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Step 2: Insert departments without department_head_id
INSERT INTO Department (budget, department_name, location)
VALUES
    (500000, 'Engineering', 'New York'),
    (200000, 'Human Resources', 'Los Angeles'),
    (300000, 'Marketing', 'Chicago'),
    (400000, 'Finance', 'San Francisco'),
    (250000, 'IT Support', 'Seattle');

-- Step 3: Insert employees
INSERT INTO Employee (city, date_of_birth, email, first_name, gender, last_name, phone, department_id)
VALUES
    ('New York', '1980-05-15', 'alice.johnson@example.com', 'Alice', 'Female', 'Johnson', '123-456-7890', 1),
    ('Los Angeles', '1975-08-22', 'bob.smith@example.com', 'Bob', 'Male', 'Smith', '987-654-3210', 2),
    ('Chicago', '1982-03-10', 'charlie.davis@example.com', 'Charlie', 'Male', 'Davis', '555-123-4567', 3),
    ('San Francisco', '1990-11-30', 'diana.white@example.com', 'Diana', 'Female', 'White', '444-987-6543', 4),
    ('Seattle', '1988-07-19', 'edward.green@example.com', 'Edward', 'Male', 'Green', '333-876-5432', 5);

-- Step 4: Update department_head_id now that employees exist
UPDATE Department SET department_head_id = 1 WHERE department_name = 'Engineering';
UPDATE Department SET department_head_id = 2 WHERE department_name = 'Human Resources';
UPDATE Department SET department_head_id = 3 WHERE department_name = 'Marketing';
UPDATE Department SET department_head_id = 4 WHERE department_name = 'Finance';
UPDATE Department SET department_head_id = 5 WHERE department_name = 'IT Support';

-- Insert mockup data into the project table
INSERT INTO project (project_id, project_name, project_description, project_status, start_date, end_date, project_head_id)
VALUES
    (101, 'Project Alpha', 'Develop a new employee management system', 'IN_PROGRESS', '2023-10-01 09:00:00', '2024-03-31 18:00:00', 1),
    (102, 'Project Beta', 'Upgrade the existing HR software', 'PLANNED', '2024-01-15 10:00:00', '2024-06-30 17:00:00', 2),
    (103, 'Project Gamma', 'Implement a new payroll system', 'COMPLETED', '2023-05-01 08:30:00', '2023-09-30 16:00:00', 3),
    (104, 'Project Delta', 'Migrate to a cloud-based infrastructure', 'IN_PROGRESS', '2023-11-01 09:00:00', '2024-02-28 18:00:00', 4),
    (105, 'Project Epsilon', 'Develop a training portal for employees', 'PLANNED', '2024-02-01 10:00:00', '2024-07-31 17:00:00', 5);

-- Insert mock data into department_project table
INSERT INTO department_project (department_id, project_id) VALUES (1, 101);
INSERT INTO department_project (department_id, project_id) VALUES (1, 102);
INSERT INTO department_project (department_id, project_id) VALUES (2, 103);
INSERT INTO department_project (department_id, project_id) VALUES (2, 104);
INSERT INTO department_project (department_id, project_id) VALUES (3, 105);

-- Insert mock data into employee_project table
INSERT INTO employee_project (project_id, employee_id) VALUES (101, 1);
INSERT INTO employee_project (project_id, employee_id) VALUES (102, 2);
INSERT INTO employee_project (project_id, employee_id) VALUES (103, 3);
INSERT INTO employee_project (project_id, employee_id) VALUES (104, 4);
INSERT INTO employee_project (project_id, employee_id) VALUES (105, 5);
INSERT INTO employee_project (project_id, employee_id) VALUES (101, 6);
INSERT INTO employee_project (project_id, employee_id) VALUES (102, 7);

INSERT INTO users (user_id, email, is_enabled, password, role) VALUES
       (1, 'baisya14shuvashree@gmail.com', 'true', '$2a$10$ExampleHashedPassword1', 'ROLE_ADMIN');
--        (2, 'user@example.com', 'true', '$2a$10$ExampleHashedPassword2', 'ROLE_USER'),
--        (3, 'manager@example.com', 'true', '$2a$10$ExampleHashedPassword3', 'ROLE_EDITOR'),
--        (4, 'guest@example.com', 'false', '$2a$10$ExampleHashedPassword4', 'ROLE_GUEST'),
--        (5, 'test@example.com', 'true', '$2a$10$ExampleHashedPassword5', 'ROLE_USER');

-- Step 5: Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;
