package com.kb.kiwibugback.employee;

import com.kb.kiwibugback.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kb.kiwibugback.project.ProjectRepository;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000/")
    List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000/")
    Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{employeeId}/projects/{projectId}")
    @CrossOrigin(origins = "http://localhost:3000/")
    Employee assignProjectToEmployee(
                @PathVariable Long projectId,
                @PathVariable Long employeeId
        ) {
            Project project = projectRepository.findById(projectId).get();
            Employee employee = employeeRepository.findById(employeeId).get();
            employee.setProject(project);
            return employeeRepository.save(employee);
        }
}
