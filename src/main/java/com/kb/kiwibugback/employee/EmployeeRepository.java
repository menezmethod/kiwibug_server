package com.kb.kiwibugback.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Set<Employee> findByEmployeeName(String employeeName);

    Optional<Employee> findByUsername(String username);

    List<Employee> findByCreatedByIsNull();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}