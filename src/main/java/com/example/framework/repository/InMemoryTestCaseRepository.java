package com.example.framework.repository;

import com.example.framework.model.TestCase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTestCaseRepository implements TestCaseRepository {

    private final Map<UUID, TestCase> storage = new ConcurrentHashMap<>();

    @Override
    public List<TestCase> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<TestCase> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public TestCase save(TestCase testCase) {
        if (testCase.getId() == null) {
            testCase.setId(UUID.randomUUID());
        }
        storage.put(testCase.getId(), testCase);
        return testCase;
    }

    @Override
    public void delete(UUID id) {
        storage.remove(id);
    }
}
