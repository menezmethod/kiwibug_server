package com.kb.kiwibugback.project;

import com.kb.kiwibugback.employee.Employee;
import com.kb.kiwibugback.employee.EmployeeRepository;
import com.kb.kiwibugback.issue.Issue;
import com.kb.kiwibugback.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

        @Autowired
        ProjectRepository projectRepository;

        @Autowired
        EmployeeRepository employeeRepository;

        @Autowired
        IssueRepository issueRepository;

        @GetMapping
        List<Project> getProjects() {
            return projectRepository.findAll();
        }

        @PostMapping
        Project createProject(@RequestBody Project project) {
            return projectRepository.save(project);
        }

//        @PutMapping("/{projectId}/employees/{employeeId}")
//        Project addEmployeeToProject(
//                @PathVariable Long projectId,
//                @PathVariable Long employeeId
//        ) {
//            Project project = projectRepository.findById(projectId).get();
//            Employee employee = employeeRepository.findById(employeeId).get();
//            project.assignEmployees.add(employee);
//            return projectRepository.save(project);
//        }
//
//        @PutMapping("/{projectId}/issue/{issueId}")
//        Project assignIssueToProject(
//                @PathVariable Long projectId,
//                @PathVariable Long issueId
//        ) {
//            Project project = projectRepository.findById(projectId).get();
//            Issue issue = issueRepository.findById(issueId).get();
//            project.setIssue(issue);
//            return projectRepository.save(project);
//        }
}
