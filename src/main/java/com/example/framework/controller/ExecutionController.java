package com.example.framework.controller;

import com.example.framework.model.ExecutionResult;
import com.example.framework.service.ExecutionService;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/executions")
public class ExecutionController {

    private final ExecutionService executionService;

    public ExecutionController(ExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping("/{caseId}")
    public ExecutionResult execute(@PathVariable UUID caseId) {
        return executionService.execute(caseId);
    }
}
