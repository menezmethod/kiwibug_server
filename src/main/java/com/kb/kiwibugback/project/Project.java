package com.kb.kiwibugback.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kb.kiwibugback.employee.Employee;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

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

    @Column(name = "project_name", nullable = false)
    private String projectName;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_date")
    private Date startDate;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "target_end_date")
    private Date targetEndDate;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "actual_end_date")
    private Date actualEndDate;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "created_by")
    private String createdBy;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "modified_on")
    private Date modifiedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "assignedProjects", cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    @ToString.Exclude
    private Set<Employee> employees;

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