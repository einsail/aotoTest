package com.example.framework.repository;

import com.example.framework.model.TestCase;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TestCaseRepository {
    List<TestCase> findAll();

    Optional<TestCase> findById(UUID id);

    TestCase save(TestCase testCase);

    void delete(UUID id);
}
