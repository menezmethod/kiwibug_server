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
        return this.employeeRepository.findAll();
    }

    @GetMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        Optional<Employee> employeeData = employeeRepository.findById(id);

        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
//    @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")
    Employee createEmployee(@RequestBody final Employee employee) {

        return this.employeeRepository.save(employee);
    }

    @PatchMapping("{id}")
//        @PreAuthorize("hasRole('USER') or hasRole('LEAD') or hasRole('MANAGER') or hasRole('ADMIN')")

    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        Optional<Employee> employeeData = employeeRepository.findById(id);

        if (employeeData.isPresent()) {
            Employee _employee = employeeData.get();
            _employee.setEmployeeName(employee.getEmployeeName());
            _employee.setEmail(employee.getEmail());
            _employee.setUsername(employee.getUsername());
            _employee.setAssignedProjects(employee.getAssignedProjects());
            if (employee.getPassword() != null) {
                _employee.setPassword(this.encoder.encode(employee.getPassword()));
            }
            return new ResponseEntity<>(employeeRepository.save(_employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{employeeId}/projects/{projectId}")
//    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
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
