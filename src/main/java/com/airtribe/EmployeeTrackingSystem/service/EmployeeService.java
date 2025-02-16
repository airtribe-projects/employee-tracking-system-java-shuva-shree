package com.airtribe.EmployeeTrackingSystem.service;

import com.airtribe.EmployeeTrackingSystem.model.Department;
import com.airtribe.EmployeeTrackingSystem.model.Employee;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import com.airtribe.EmployeeTrackingSystem.repo.EmployeeRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;


    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }


    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }


    public Employee getEmployeeById(int employeeId) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
        return optionalEmployee.orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));
    }


    public Employee updateEmployee(int employeeId, Employee employee) {
//        Session session = sessionFactory.getCurrentSession();
        Employee existingEmployee = employeeRepo.findById(employeeId).orElse(null);
        if (existingEmployee != null) {

            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhone(employee.getPhone());
            existingEmployee.setCity(employee.getCity());
            existingEmployee.setDateOfBirth(employee.getDateOfBirth());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setProjectList(employee.getProjectList());
            return employeeRepo.save(existingEmployee);
        } else {
            throw new IllegalArgumentException("Employee not found");

        }
    }

    public void deleteEmployee(int employeeId) {
        Employee employee = getEmployeeById(employeeId); // Check if employee exists
        employeeRepo.delete(employee);
    }


    public List<Employee> searchEmployeeByKeyword(String keyword) {
        return employeeRepo.searchEmployees(keyword);
    }

    public List<Employee> getEmployeesInProject(Integer projectId) {
        return employeeRepo.getEmployeesInProject(projectId);
    }


    public List<Employee> getEmployeesWithoutProjects() {
        return employeeRepo.findEmployeesWithoutProjects();
    }


}