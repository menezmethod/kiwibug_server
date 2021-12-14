package com.kb.kiwibugback.issue;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kb.kiwibugback.employee.Employee;
import com.kb.kiwibugback.project.Project;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "issue", indexes = {
        @Index(name = "idx_issue_summary", columnList = "issue_summary")
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id", nullable = false)
    private Long issuesId;

    @Column(name = "issue_summary", nullable = false)
    private String issueSummary;

    @Lob
    @Column(name = "issue_description")
    private String issueDescription;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "identified_date")
    private LocalDateTime identifiedDate;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "priority", length = 30)
    private String priority;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "target_resolution_date")
    private LocalDateTime targetResolutionDate;

    @Lob
    @Column(name = "progress")
    private String progress;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "actual_resolution_date")
    private LocalDateTime actualResolutionDate;

    @Lob
    @Column(name = "resolution_summary")
    private String resolutionSummary;

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
    @JoinColumn(name = "assigned_to_employee_id")
    private Employee assignedToEmployeeId;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "identified_by_employee_id")
    private Employee identifiedByEmployeeId;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "related_project_id")
    private Project relatedProjectId;

    public void setIssueToEmployeeId(Employee employee) {
        this.assignedToEmployeeId = employee;
    }
    public void setIdentifiedByEmployeeId(Employee employee) {
        this.identifiedByEmployeeId = employee;
    }
    public void setProject(Project project) {
        this.relatedProjectId = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Issue issue = (Issue) o;
        return issuesId != null && Objects.equals(issuesId, issue.issuesId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}