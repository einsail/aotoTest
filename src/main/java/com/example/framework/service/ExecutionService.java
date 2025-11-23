package com.example.framework.service;

import com.example.framework.model.ExecutionResult;
import com.example.framework.model.ExecutionStatus;
import com.example.framework.model.StepResult;
import com.example.framework.model.TestCase;
import com.example.framework.model.TestStep;
import com.example.framework.page.PageActionRegistry;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ExecutionService {

    private final TestCaseService testCaseService;
    private final PageActionRegistry registry;

    public ExecutionService(TestCaseService testCaseService, PageActionRegistry registry) {
        this.testCaseService = testCaseService;
        this.registry = registry;
    }

    public ExecutionResult execute(UUID caseId) {
        TestCase testCase = testCaseService.findById(caseId);
        ExecutionResult result = new ExecutionResult();
        result.setCaseId(caseId);
        List<StepResult> stepResults = new ArrayList<>();

        for (TestStep step : testCase.getSteps()) {
            StepResult stepResult = registry.getHandler(step.getPage())
                    .map(handler -> handler.execute(step))
                    .orElseGet(() -> buildMissingHandlerResult(step));
            stepResults.add(stepResult);
        }

        result.setSteps(stepResults);
        boolean hasFailure = stepResults.stream().anyMatch(r -> r.getStatus() == ExecutionStatus.FAIL);
        result.setStatus(hasFailure ? ExecutionStatus.FAIL : ExecutionStatus.PASS);
        result.setFinishedAt(Instant.now());
        return result;
    }

    private StepResult buildMissingHandlerResult(TestStep step) {
        StepResult result = new StepResult();
        result.setAction(step.getAction());
        result.setPage(step.getPage());
        result.setStatus(ExecutionStatus.FAIL);
        result.setErrorMessage("No handler registered for page: " + step.getPage());
        return result;
    }
}
