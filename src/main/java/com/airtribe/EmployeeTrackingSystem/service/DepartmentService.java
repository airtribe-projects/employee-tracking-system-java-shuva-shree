package com.airtribe.EmployeeTrackingSystem.service;

import com.airtribe.EmployeeTrackingSystem.model.Department;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import com.airtribe.EmployeeTrackingSystem.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;


    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    public Department getDepartmentById(int id) {
        return departmentRepo.findById(id).orElse(null);
    }

    public Department addDepartment(Department department) {
        return departmentRepo.save(department);
    }

    public Department updateDepartment(int departmentId,Department department) {
        Department existingDepartment = departmentRepo.findById(departmentId).orElse(null);;
        if (existingDepartment != null) {


            existingDepartment.setDepartmentName(department.getDepartmentName());
            existingDepartment.setDepartmentHead(department.getDepartmentHead());
            existingDepartment.setBudget(department.getBudget());
            existingDepartment.setLocation(department.getLocation());
            existingDepartment.setEmployees(department.getEmployees());
            existingDepartment.setProjectList(department.getProjectList());
            departmentRepo.save(existingDepartment);
        }else{
                throw new IllegalArgumentException("Department not found");
            }
        return null;

    }
    public void deleteDepartment(int id) {
        departmentRepo.deleteById(id);

    }

    public Double getTotalBudegt(int departmentId) {
        return departmentRepo.getTotalBudgetForDepartment(departmentId);
    }

    public List<Project> getProjectsByDepartment(int departmentId) {
        return departmentRepo.getProjectsByDepartment(departmentId);
    }
}
