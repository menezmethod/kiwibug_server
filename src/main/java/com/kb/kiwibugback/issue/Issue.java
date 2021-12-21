package com.kb.kiwibugback.issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kb.kiwibugback.employee.Employee;
import com.kb.kiwibugback.project.Project;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "issue", indexes = @Index(name = "idx_issue_summary", columnList = "issue_summary"))
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

    @Column(name = "issue_description", columnDefinition="text")
    private String issueDescription;

    @Column(name = "identified_date")
    private LocalDate identifiedDate;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "priority", length = 30)
    private String priority;

    @Column(name = "target_resolution_date")
    private LocalDate targetResolutionDate;


    @Column(name = "progress", columnDefinition="text")
    private String progress;

    @Column(name = "actual_resolution_date")
    private LocalDate actualResolutionDate;


    @Column(name = "resolution_summary", columnDefinition="text")
    private String resolutionSummary;

    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @UpdateTimestamp
    @Column(name = "modified_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to_employee_id")
    private Employee assignedToEmployeeId;

    @ManyToOne
    @JoinColumn(name = "identified_by_employee_id")
    private Employee identifiedByEmployeeId;

    @ManyToOne
    @JoinColumn(name = "related_project_id")
    private Project relatedProjectId;

    @JsonCreator
    public Issue(final String issueSummary, final String issueDescription, final LocalDate identifiedDate, final String status, final String priority, final String progress, final LocalDate targetResolutionDate, final LocalDate actualResolutionDate, final String resolutionSummary, final String createdBy, final String modifiedBy, Employee assignedToEmployeeId, Employee identifiedByEmployeeId, Project relatedProjectId) {
        this.issueSummary = issueSummary;
        this.issueDescription = issueDescription;
        this.identifiedDate = identifiedDate;
        this.actualResolutionDate = actualResolutionDate;
        this.status = status;
        this.priority = priority;
        this.progress = progress;
        this.targetResolutionDate = targetResolutionDate;
        this.resolutionSummary = resolutionSummary;
        this.createdBy = createdBy;
        this.assignedToEmployeeId = assignedToEmployeeId;
        this.identifiedByEmployeeId = identifiedByEmployeeId;
        this.relatedProjectId = relatedProjectId;
        // REPLACE WHEN AUTH
        this.modifiedBy = modifiedBy;
    }

    public void setIssueToEmployeeId(final Employee employee) {
        assignedToEmployeeId = employee;
    }
    public void setIdentifiedByEmployeeId(final Employee employee) {
        identifiedByEmployeeId = employee;
    }
    public void setProject(final Project project) {
        relatedProjectId = project;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final Issue issue = (Issue) o;
        return this.issuesId != null && Objects.equals(this.issuesId, issue.issuesId);
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

//    public class IssueJsonDeserializer extends JsonDeserializer<Issue> {
//        @Override
//        public Issue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//            if(jsonParser == null)return null;
//
//            Issue issue = new Issue();
//            issue.setIdentifiedByEmployeeId(Integer.valueOf(jsonParser.getText()));
//            return issue;
//        }
//    }

}