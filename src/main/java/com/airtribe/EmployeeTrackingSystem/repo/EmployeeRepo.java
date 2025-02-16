package com.airtribe.EmployeeTrackingSystem.repo;

import com.airtribe.EmployeeTrackingSystem.model.Department;
import com.airtribe.EmployeeTrackingSystem.model.Employee;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    @Query("SELECT e "+
            "FROM Employee e " +
            "LEFT JOIN e.department d " +
            "LEFT JOIN e.projectList p " +
            "WHERE (:keyword IS NULL OR e.firstName LIKE %:keyword% " +
            "OR e.lastName LIKE %:keyword% " +
            "OR d.departmentName LIKE %:keyword% " +
            "OR p.projectName LIKE %:keyword%)")
    List<Employee> searchEmployees(@Param("keyword") String keyword);


    @Query("SELECT e FROM Employee e " +
            "JOIN e.projectList p " +
            "WHERE p.projectId = :projectId")
    List<Employee> getEmployeesInProject(Integer projectId);


    @Query("SELECT e FROM Employee e WHERE e.projectList IS EMPTY")
    List<Employee> findEmployeesWithoutProjects();

}
