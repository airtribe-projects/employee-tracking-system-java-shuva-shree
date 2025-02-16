# Employee Management System

## Overview

The Employee Management System is a Java-based web application built using Spring Boot. It allows for comprehensive management of employees, departments, and projects with advanced features. The application supports full CRUD operations, secure authentication and authorization using OAuth2.0 and OpenID Connect, advanced database interactions, caching strategies, and comprehensive unit testing.

## Features

### CRUD Operations

- **Employees:** Add, update, delete, and view employees.
- **Departments:** Add, update, delete, and view departments.
- **Projects:** Add, update, delete, and view projects.

- Used MySQL as the runtime database and H2 database for testing.
- Utilize Spring Data JPA to interact with the database.
- Custom queries for advanced operations:
  - Search employees by various criteria (name, department, project).
  - Calculate the total budget allocated to projects within a department.
  - Find employees who have not been assigned to any project.
  - Retrieve all employees working on a specific project.

### OAuth2.0 and OpenID Connect Integration

- Implemented OAuth2.0 with OpenID Connect for user authentication and authorization.
- Integrated with an external Identity Provider Google Cloud for user management.
- Protected the application’s endpoints using roles (ADMIN, MANAGER, EMPLOYEE).

### Role-Based Access Control

- **ADMIN:** Full access to all CRUD operations and administrative functions.
- **MANAGER:** Access to modify and view employees and projects within their department.
- **EMPLOYEE:** Access to view their own profile and assigned projects.

### API Endpoints

- **Employees:**
  - `/employees` - Accessible by ADMIN and MANAGER.
  - `/employees/{id}` - Accessible by ADMIN, MANAGER (for employees in their department), and the EMPLOYEE themselves.
- **Departments:**
  - `/departments` - Accessible by ADMIN and MANAGER.
- **Projects:**
  - `/projects` - Accessible by ADMIN and MANAGER.
- **Additional Endpoints:**
  - `/employees/search` - Search employees by various criteria (accessible by ADMIN and MANAGER).
  - `/departments/{id}/projects` - List all projects under a specific department (accessible by ADMIN and MANAGER).


### Unit Testing

- Written comprehensive unit tests using JUnit and Mockito for:
  - **Controllers:** Test all API endpoints for expected behavior, including authenticated and unauthenticated access.
  - **Services:** Test business logic, caching, and validation rules.
  - **Repositories:** Test data access layer, including custom queries.

