package com.airtribe.EmployeeTrackingSystem.controller;

import com.airtribe.EmployeeTrackingSystem.model.Department;
import com.airtribe.EmployeeTrackingSystem.model.Employee;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import com.airtribe.EmployeeTrackingSystem.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') and @employeeService.isManagerOfDepartment(principal, #id)) or (principal.username == @employeeService.getEmployeeEmail(#id))")
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer employeeId) {
        Employee getEmployee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(getEmployee, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(employeeId, employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployeeByKeyword(@RequestParam String keyword) {
        List<Employee> employeeList = employeeService.searchEmployeeByKeyword(keyword);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("byProject/{projectId}")
    public ResponseEntity<List<Employee>> getEmployeesInProject(@PathVariable Integer projectId) {
        List<Employee> employeeList = employeeService.getEmployeesInProject(projectId);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/without-projects")
    public ResponseEntity<List<Employee>> getEmployeesWithoutProjects() {
        List<Employee> employees = employeeService.getEmployeesWithoutProjects();
        return ResponseEntity.ok(employees);
    }
}

