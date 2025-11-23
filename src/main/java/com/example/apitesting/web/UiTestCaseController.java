package com.example.apitesting.web;

import com.example.apitesting.model.UiTestCase;
import com.example.apitesting.model.UiTestResult;
import com.example.apitesting.service.UiTestCaseService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testcases")
public class UiTestCaseController {

    private final UiTestCaseService testCaseService;

    public UiTestCaseController(UiTestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @GetMapping
    public List<UiTestCase> list() {
        return testCaseService.getTestCases();
    }

    @PostMapping
    public ResponseEntity<UiTestCase> create(@RequestBody UiTestCase request) {
        UiTestCase created = testCaseService.addTestCase(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/run")
    public List<UiTestResult> runAll() {
        return testCaseService.runAll();
    }
}
