package com.airtribe.EmployeeTrackingSystem.repo;

import com.airtribe.EmployeeTrackingSystem.model.Department;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {

    @Query("SELECT SUM(d.budget) FROM Department d JOIN d.projectList p WHERE d.departmentId = :departmentId")
    Double getTotalBudgetForDepartment(int departmentId);

    @Query("SELECT p FROM Department d " +
            "JOIN d.projectList p " +
            "WHERE d.departmentId = :departmentId")
    List<Project> getProjectsByDepartment(int departmentId);
}
