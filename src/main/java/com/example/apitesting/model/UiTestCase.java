package com.example.apitesting.model;

import java.time.OffsetDateTime;

/**
 * Represents a user-defined API test case that can be executed via the UI.
 */
public class UiTestCase {

    private Long id;
    private String name;
    private String description;
    private String path;
    private String method;
    private int expectedStatus;
    private OffsetDateTime lastRunAt;
    private UiTestResult lastResult;

    public UiTestCase() {
    }

    public UiTestCase(Long id, String name, String description, String path, String method, int expectedStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.path = path;
        this.method = method;
        this.expectedStatus = expectedStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getExpectedStatus() {
        return expectedStatus;
    }

    public void setExpectedStatus(int expectedStatus) {
        this.expectedStatus = expectedStatus;
    }

    public OffsetDateTime getLastRunAt() {
        return lastRunAt;
    }

    public void setLastRunAt(OffsetDateTime lastRunAt) {
        this.lastRunAt = lastRunAt;
    }

    public UiTestResult getLastResult() {
        return lastResult;
    }

    public void setLastResult(UiTestResult lastResult) {
        this.lastResult = lastResult;
    }
}
