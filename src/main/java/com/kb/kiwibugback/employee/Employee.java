package com.kb.kiwibugback.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kb.kiwibugback.project.Project;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Email
    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "employee_role", length = 30)
    private String employeeRole;

    @Column(name = "username")
    private String username;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "assigned_projects")
    private Project assignedProjects;

    public void setProject(Project project) {
        this.assignedProjects = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return employeeId != null && Objects.equals(employeeId, employee.employeeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}