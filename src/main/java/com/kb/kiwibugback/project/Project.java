package com.kb.kiwibugback.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kb.kiwibugback.employee.Employee;
import com.kb.kiwibugback.issue.Issue;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "project", indexes = @Index(name = "idx_project_name", columnList = "project_name"))
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "target_end_date")
    private LocalDate targetEndDate;

    @Column(name = "actual_end_date")
    private LocalDate actualEndDate;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "assignedProjects", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Employee> employees;

    @JsonIgnore
    @OneToMany(mappedBy = "relatedProjectId", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Issue> issues;

    public Project(String projectName, LocalDate startDate, LocalDate targetEndDate, LocalDate actualEndDate, boolean b) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.targetEndDate = targetEndDate;
        this.actualEndDate = actualEndDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final Project project = (Project) o;
        return this.projectId != null && Objects.equals(this.projectId, project.projectId);
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
}