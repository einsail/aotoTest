package com.example.framework.controller;

import com.example.framework.model.TestCase;
import com.example.framework.service.TestCaseService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cases")
public class TestCaseController {

    private final TestCaseService service;

    public TestCaseController(TestCaseService service) {
        this.service = service;
    }

    @GetMapping
    public List<TestCase> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TestCase get(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<TestCase> create(@Valid @RequestBody TestCase testCase) {
        TestCase created = service.create(testCase);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public TestCase update(@PathVariable UUID id, @Valid @RequestBody TestCase testCase) {
        return service.update(id, testCase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
