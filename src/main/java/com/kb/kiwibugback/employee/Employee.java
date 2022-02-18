package com.kb.kiwibugback.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kb.kiwibugback.issue.Issue;
import com.kb.kiwibugback.project.Project;
import com.kb.kiwibugback.role.Role;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee", indexes = @Index(name = "idx_employee_name", columnList = "employee_name"))
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Email
    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public Employee(String username, String email, String password, String employeeName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.employeeName = employeeName;
        assignedProjects = this.assignedProjects;
    }

    @Column(name = "username")
    private String username;

    @Size(max = 120)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "assigned_projects")
    private Project assignedProjects;

    @JsonIgnore
    @OneToMany(mappedBy = "identifiedByEmployeeId", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Issue> identifiedByEmployeeId;

    @JsonIgnore
    @OneToMany(mappedBy = "assignedToEmployeeId", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Issue> assignedToEmployeeId;

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
