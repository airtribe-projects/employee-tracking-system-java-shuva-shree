package com.airtribe.EmployeeTrackingSystem.controller;


import com.airtribe.EmployeeTrackingSystem.model.Department;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import com.airtribe.EmployeeTrackingSystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
    }

    @GetMapping
    public List<Department> getAllDepartments(@RequestBody Department department) {
        return departmentService.getAllDepartments();

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department newDepartment = departmentService.addDepartment(department);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable int departmentId, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(departmentId, department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable int departmentId) {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/totalBudget/{departmentId}")
    public ResponseEntity<Double> getTotalBudget(@PathVariable int departmentId) {
        Double totalBudget = departmentService.getTotalBudegt(departmentId);
        return new ResponseEntity<>(totalBudget,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/{departmentId}/projects")
    public ResponseEntity<List<Project>> getProjectsByDepartment(@PathVariable int departmentId) {
        List<Project> projectList = departmentService.getProjectsByDepartment(departmentId);
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
}
