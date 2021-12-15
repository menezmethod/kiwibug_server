package com.kb.kiwibugback.employee;

import com.kb.kiwibugback.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.kb.kiwibugback.project.ProjectRepository;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping
//    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    Employee createEmployee(@RequestBody final Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @PutMapping("/{employeeId}/projects/{projectId}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    Employee assignProjectToEmployee(
            @PathVariable final Long projectId,
            @PathVariable final Long employeeId
    ) {
        final Project project = this.projectRepository.findById(projectId).get();
        final Employee employee = this.employeeRepository.findById(employeeId).get();
        employee.setProject(project);
        return this.employeeRepository.save(employee);
    }
}
