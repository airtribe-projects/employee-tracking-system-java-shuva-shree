package com.airtribe.EmployeeTrackingSystem.repo;

import com.airtribe.EmployeeTrackingSystem.model.Employee;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project,Integer> {


}
