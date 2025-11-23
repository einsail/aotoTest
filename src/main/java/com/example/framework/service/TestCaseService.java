package com.example.framework.service;

import com.example.framework.model.TestCase;
import com.example.framework.repository.TestCaseRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TestCaseService {

    private final TestCaseRepository repository;

    public TestCaseService(TestCaseRepository repository) {
        this.repository = repository;
    }

    public List<TestCase> findAll() {
        return repository.findAll();
    }

    public TestCase findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Test case not found: " + id));
    }

    public TestCase create(TestCase testCase) {
        return repository.save(testCase);
    }

    public TestCase update(UUID id, TestCase updated) {
        TestCase existing = findById(id);
        updated.setId(existing.getId());
        return repository.save(updated);
    }

    public void delete(UUID id) {
        repository.delete(id);
    }
}
