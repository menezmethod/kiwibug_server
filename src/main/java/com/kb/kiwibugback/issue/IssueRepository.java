package com.kb.kiwibugback.issue;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByIssueSummary(String issueSummary);

    List<Issue> findByTargetResolutionDateIsNull();

    long deleteByIssuesId(Long issuesId);
}