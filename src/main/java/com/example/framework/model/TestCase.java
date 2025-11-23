package com.example.framework.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class TestCase {
    private UUID id;

    @NotBlank
    private String name;

    private String description;

    @Valid
    private List<TestStep> steps = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public List<TestStep> getSteps() {
        return Collections.unmodifiableList(steps);
    }

    public void setSteps(List<TestStep> steps) {
        this.steps = steps == null ? new ArrayList<>() : new ArrayList<>(steps);
    }
}
