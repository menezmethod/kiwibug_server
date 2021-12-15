package com.kb.kiwibugback.issue;

import com.kb.kiwibugback.employee.Employee;
import com.kb.kiwibugback.employee.EmployeeRepository;
import com.kb.kiwibugback.project.Project;
import com.kb.kiwibugback.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    IssueRepository issueRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping
//    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    List<Issue> getIssues() {
        return this.issueRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    Issue createIssue(@RequestBody final Issue issue) {
        return this.issueRepository.save(issue);
    }

    @PutMapping("/{issueId}/employees/assign/{employeeId}")
    @PreAuthorize("hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    Issue assignToEmployee(
            @PathVariable final Long issueId,
            @PathVariable final Long employeeId
    ) {
        final Issue issue = this.issueRepository.findById(issueId).get();
        final Employee employee = this.employeeRepository.findById(employeeId).get();
        issue.setAssignedToEmployeeId(employee);
        return this.issueRepository.save(issue);
    }

    @PutMapping("/{issueId}/employees/id/{employeeId}")
    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    Issue identifiedByEmployee(
            @PathVariable final Long issueId,
            @PathVariable final Long employeeId
    ) {
        final Issue issue = this.issueRepository.findById(issueId).get();
        final Employee employee = this.employeeRepository.findById(employeeId).get();
        issue.setIdentifiedByEmployeeId(employee);
        return this.issueRepository.save(issue);
    }

    @PutMapping("/{issueId}/projects/{projectId}")
    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    Issue assignProjectToIssue(
            @PathVariable final Long issueId,
            @PathVariable final Long projectId
    ) {
        final Issue issue = this.issueRepository.findById(issueId).get();
        final Project project = this.projectRepository.findById(projectId).get();
        issue.setProject(project);
        return this.issueRepository.save(issue);
    }
}
