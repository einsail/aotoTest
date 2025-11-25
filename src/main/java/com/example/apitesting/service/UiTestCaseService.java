package com.example.apitesting.service;

import com.example.apitesting.config.EnvironmentProperties;
import com.example.apitesting.model.UiTestCase;
import com.example.apitesting.model.UiTestResult;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class UiTestCaseService {

    private static final Logger LOG = LoggerFactory.getLogger(UiTestCaseService.class);

    private final EnvironmentProperties environmentProperties;
    private final RestTemplate restTemplate;
    private final List<UiTestCase> testCases = new CopyOnWriteArrayList<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public UiTestCaseService(EnvironmentProperties environmentProperties, RestTemplateBuilder restTemplateBuilder) {
        this.environmentProperties = environmentProperties;
        this.restTemplate = restTemplateBuilder.build();
        seedDefaults();
    }

    public List<UiTestCase> getTestCases() {
        return testCases;
    }

    public UiTestCase addTestCase(UiTestCase request) {
        UiTestCase testCase = new UiTestCase(
                idSequence.getAndIncrement(),
                request.getName(),
                request.getDescription(),
                request.getPath(),
                request.getMethod(),
                request.getExpectedStatus()
        );
        testCases.add(testCase);
        LOG.info("Added UI test case: {} {}", testCase.getMethod(), testCase.getPath());
        return testCase;
    }

    public List<UiTestResult> runAll() {
        List<UiTestResult> results = new ArrayList<>();
        for (UiTestCase testCase : testCases) {
            results.add(runSingle(testCase));
        }
        return results;
    }

    private UiTestResult runSingle(UiTestCase testCase) {
        OffsetDateTime executedAt = OffsetDateTime.now();
        Integer actualStatus = null;
        boolean passed = false;
        String details;
        try {
            URI requestUri = URI.create(environmentProperties.getBaseUrl() + testCase.getPath());
            LOG.info("Running UI test case {} {}", testCase.getMethod(), requestUri);
            ResponseEntity<String> response = restTemplate.exchange(
                    requestUri,
                    HttpMethod.valueOf(testCase.getMethod()),
                    HttpEntity.EMPTY,
                    String.class
            );
            actualStatus = response.getStatusCode().value();
            passed = actualStatus == testCase.getExpectedStatus();
            details = response.getBody();
        } catch (RestClientResponseException e) {
            actualStatus = e.getRawStatusCode();
            passed = actualStatus == testCase.getExpectedStatus();
            details = e.getResponseBodyAsString();
        } catch (Exception e) {
            details = e.getMessage();
        }

        UiTestResult result = new UiTestResult(
                testCase.getId(),
                testCase.getName(),
                passed,
                actualStatus,
                testCase.getExpectedStatus(),
                details,
                executedAt
        );
        testCase.setLastResult(result);
        testCase.setLastRunAt(executedAt);
        return result;
    }

    private void seedDefaults() {
        if (!testCases.isEmpty()) {
            return;
        }
        addTestCase(new UiTestCase(null, "Health check", "Verify /api/health returns UP", "/api/health", "GET", 200));
        addTestCase(new UiTestCase(null, "List users", "Retrieve demo users", "/api/users", "GET", 200));
    }
}
