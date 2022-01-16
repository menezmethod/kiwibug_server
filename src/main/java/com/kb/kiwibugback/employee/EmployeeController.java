package com.kb.kiwibugback.employee;

import com.kb.kiwibugback.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.kb.kiwibugback.project.ProjectRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping
//    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") final long id) {
        final Optional<Employee> employeeData = this.employeeRepository.findById(id);

        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
//    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    Employee createEmployee(@RequestBody Employee employee) {

        return employeeRepository.save(employee);
    }

    @PatchMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")

    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") final long id, @RequestBody final Employee employee) {
        final Optional<Employee> employeeData = this.employeeRepository.findById(id);

        if (employeeData.isPresent()) {
            final Employee _employee = employeeData.get();
            _employee.setEmployeeName(employee.getEmployeeName());
            _employee.setEmail(employee.getEmail());
            _employee.setUsername(employee.getUsername());
            _employee.setAssignedProjects(employee.getAssignedProjects());
            if (employee.getPassword() != null) {
                _employee.setPassword(encoder.encode(employee.getPassword()));
            }
            return new ResponseEntity<>(this.employeeRepository.save(_employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{employeeId}/projects/{projectId}")
//    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    Employee assignProjectToEmployee(
            @PathVariable Long projectId,
            @PathVariable Long employeeId
    ) {
        Project project = projectRepository.findById(projectId).get();
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setProject(project);
        return employeeRepository.save(employee);
    }
    @DeleteMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")

    public ResponseEntity<HttpStatus> deleteIssue(@PathVariable("id") final long id) {
        try {
            this.employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (final Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
