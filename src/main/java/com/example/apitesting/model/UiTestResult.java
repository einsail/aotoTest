package com.example.apitesting.model;

import java.time.OffsetDateTime;

/**
 * Captures the outcome of a UI-triggered automation run.
 */
public class UiTestResult {

    private Long testCaseId;
    private String testCaseName;
    private boolean passed;
    private Integer actualStatus;
    private int expectedStatus;
    private String details;
    private OffsetDateTime executedAt;

    public UiTestResult() {
    }

    public UiTestResult(Long testCaseId, String testCaseName, boolean passed, Integer actualStatus,
                         int expectedStatus, String details, OffsetDateTime executedAt) {
        this.testCaseId = testCaseId;
        this.testCaseName = testCaseName;
        this.passed = passed;
        this.actualStatus = actualStatus;
        this.expectedStatus = expectedStatus;
        this.details = details;
        this.executedAt = executedAt;
    }

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Integer getActualStatus() {
        return actualStatus;
    }

    public void setActualStatus(Integer actualStatus) {
        this.actualStatus = actualStatus;
    }

    public int getExpectedStatus() {
        return expectedStatus;
    }

    public void setExpectedStatus(int expectedStatus) {
        this.expectedStatus = expectedStatus;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public OffsetDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(OffsetDateTime executedAt) {
        this.executedAt = executedAt;
    }
}
