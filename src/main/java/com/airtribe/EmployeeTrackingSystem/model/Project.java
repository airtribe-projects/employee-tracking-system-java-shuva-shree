package com.airtribe.EmployeeTrackingSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "projectId")
public class Project {
    @Id
    private int projectId;
    private String projectName;
    private String projectDescription;
    private Date startDate;
    private Date endDate;
    private String projectStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_head_id")
    private Employee projectHead;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
//    @JsonBackReference
    private List<Employee> employeeList;
    public Project() {
    }

    public Project(int projectId, String projectName, String projectDescription, Date startDate, Date endDate, String projectStatus,Employee projectHead, List<Employee> employeeList) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectStatus = projectStatus;
        this.projectHead = projectHead;
        this.employeeList = employeeList;
    }

    public int getProjectId() {
        return projectId;
    }

    public Employee getProjectHead() {
        return projectHead;
    }

    public void setProjectHead(Employee projectHead) {
        this.projectHead = projectHead;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

}
