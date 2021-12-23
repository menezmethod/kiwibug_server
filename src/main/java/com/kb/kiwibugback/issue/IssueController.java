package com.kb.kiwibugback.issue;

import com.kb.kiwibugback.employee.Employee;
import com.kb.kiwibugback.employee.EmployeeRepository;
import com.kb.kiwibugback.project.Project;
import com.kb.kiwibugback.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    List<Issue> getIssues() {
        return this.issueRepository.findAll();
    }

    @GetMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Issue> getIssueById(@PathVariable("id") long id) {
        Optional<Issue> issueData = issueRepository.findById(id);

        if (issueData.isPresent()) {
            return new ResponseEntity<>(issueData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
        try {
            Issue _issue = issueRepository
                    .save(new Issue(issue.getIssueSummary(), issue.getIssueDescription(), issue.getIdentifiedDate(), issue.getStatus(), issue.getPriority(), issue.getProgress(), issue.getTargetResolutionDate(), issue.getActualResolutionDate(), issue.getResolutionSummary(), issue.getCreatedBy(), issue.getModifiedBy(), issue.getAssignedToEmployeeId(), issue.getIdentifiedByEmployeeId(), issue.getRelatedProjectId()));

            return new ResponseEntity<>(_issue, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")

    public ResponseEntity<Issue> updateIssue(@PathVariable("id") long id, @RequestBody Issue issue) {
        Optional<Issue> issueData = issueRepository.findById(id);

        if (issueData.isPresent()) {
            Issue _issue = issueData.get();
            _issue.setIssueSummary(issue.getIssueSummary());
            _issue.setIssueDescription(issue.getIssueDescription());
            _issue.setIdentifiedDate(issue.getIdentifiedDate());
            _issue.setActualResolutionDate(issue.getActualResolutionDate());
            _issue.setStatus(issue.getStatus());
            _issue.setPriority(issue.getPriority());
            _issue.setProgress(issue.getProgress());
            _issue.setTargetResolutionDate(issue.getTargetResolutionDate());
            _issue.setResolutionSummary(issue.getResolutionSummary());
            _issue.setCreatedBy(issue.getCreatedBy());
            _issue.setModifiedBy(issue.getModifiedBy());
            _issue.setAssignedToEmployeeId(issue.getAssignedToEmployeeId());
            _issue.setIdentifiedByEmployeeId(issue.getIdentifiedByEmployeeId());
            _issue.setRelatedProjectId(issue.getRelatedProjectId());
            return new ResponseEntity<>(issueRepository.save(_issue), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{issueId}/employees/id/{employeeId}")
//    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
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
//    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    Issue assignProjectToIssue(
            @PathVariable final Long issueId,
            @PathVariable final Long projectId
    ) {
        final Issue issue = this.issueRepository.findById(issueId).get();
        final Project project = this.projectRepository.findById(projectId).get();
        issue.setProject(project);
        return this.issueRepository.save(issue);
    }

    @DeleteMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")

    public ResponseEntity<HttpStatus> deleteIssue(@PathVariable("id") long id) {
        try {
            issueRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/issues")
    public ResponseEntity<HttpStatus> deleteAllIssues() {
        try {
            issueRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}