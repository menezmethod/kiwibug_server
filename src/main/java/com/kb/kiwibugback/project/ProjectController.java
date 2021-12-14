package com.kb.kiwibugback.project;

import com.kb.kiwibugback.employee.EmployeeRepository;
import com.kb.kiwibugback.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

        @Autowired
        ProjectRepository projectRepository;

        @Autowired
        EmployeeRepository employeeRepository;

        @Autowired
        IssueRepository issueRepository;

        @GetMapping
        @CrossOrigin(origins = "http://localhost:3000/")
        List<Project> getProjects() {
            return projectRepository.findAll();
        }

        @PostMapping
        @CrossOrigin(origins = "http://localhost:3000/")
        Project createProject(@RequestBody Project project) {
            return projectRepository.save(project);
        }
}
