package com.airtribe.EmployeeTrackingSystem.service;


import com.airtribe.EmployeeTrackingSystem.model.Employee;
import com.airtribe.EmployeeTrackingSystem.model.Project;
import com.airtribe.EmployeeTrackingSystem.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    public List<Project> getAllProjects(){
        return projectRepo.findAll();
    }

    public Project getProjectById(int id){
        return projectRepo.findById(id).orElse(null);
    }

    public Project addProject(Project project){
        return projectRepo.save(project);
    }
    public Project updateProject(Integer projectId,Project project){
        Project existingProject = projectRepo.findById(projectId).orElse(null);
        if(existingProject != null){


        existingProject.setProjectDescription(project.getProjectDescription());
        existingProject.setProjectName(project.getProjectName());
        existingProject.setProjectStatus(project.getProjectStatus());
        existingProject.setProjectHead(project.getProjectHead());
        return projectRepo.save(existingProject);
        }else {
            return null;
        }

    }

    public void deleteProject(Integer projectId){
        projectRepo.deleteById(projectId);
    }


}
