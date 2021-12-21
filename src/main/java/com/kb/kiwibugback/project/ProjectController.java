package com.kb.kiwibugback.project;

import com.kb.kiwibugback.employee.EmployeeRepository;
import com.kb.kiwibugback.issue.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/projects")
public class ProjectController {

        @Autowired
        ProjectRepository projectRepository;

        @GetMapping
        List<Project> getProjects() {
                return this.projectRepository.findAll();
        }

        @GetMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
        public ResponseEntity<Project> getProjectById(@PathVariable("id") long id) {
                Optional<Project> projectData = projectRepository.findById(id);

                if (projectData.isPresent()) {
                        return new ResponseEntity<>(projectData.get(), HttpStatus.OK);
                } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @PostMapping
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
        public ResponseEntity<Project> createProject(@RequestBody Project project) {
                try {
                        Project _project = projectRepository
                                .save(new Project(project.getProjectName(), project.getStartDate(), project.getTargetEndDate(), project.getActualEndDate(), false));
                        return new ResponseEntity<>(_project, HttpStatus.CREATED);
                } catch (Exception e) {
                        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        @PutMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")

        public ResponseEntity<Project> updateProject(@PathVariable("id") long id, @RequestBody Project project) {
                Optional<Project> projectData = projectRepository.findById(id);

                if (projectData.isPresent()) {
                        Project _project = projectData.get();
                        _project.setProjectName(project.getProjectName());
                        _project.setStartDate(project.getStartDate());
                        _project.setTargetEndDate(project.getTargetEndDate());
                        _project.setActualEndDate(project.getActualEndDate());
                        return new ResponseEntity<>(projectRepository.save(_project), HttpStatus.OK);
                } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @DeleteMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")

        public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") long id) {
                try {
                    projectRepository.deleteById(id);
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/projects")
        public ResponseEntity<HttpStatus> deleteAllProjects() {
                try {
                    projectRepository.deleteAll();
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }

        }
}