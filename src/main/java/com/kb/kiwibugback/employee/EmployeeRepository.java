package com.kb.kiwibugback.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Set<Employee> findByEmployeeName(String employeeName);

    List<Employee> findByCreatedByIsNull();

}