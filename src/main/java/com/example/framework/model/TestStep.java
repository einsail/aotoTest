package com.example.framework.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TestStep {
    @NotBlank
    private String page;

    @NotBlank
    private String action;

    private String description;

    @NotNull
    private Map<String, Object> parameters = new HashMap<>();

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters == null ? new HashMap<>() : new HashMap<>(parameters);
    }
}
