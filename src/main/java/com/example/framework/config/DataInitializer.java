package com.example.framework.config;

import com.example.framework.model.TestCase;
import com.example.framework.model.TestStep;
import com.example.framework.page.RestApiPage;
import com.example.framework.repository.TestCaseRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final TestCaseRepository repository;

    public DataInitializer(TestCaseRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void seed() {
        if (repository.findAll().isEmpty()) {
            TestCase testCase = new TestCase();
            testCase.setName("Echo API health");
            testCase.setDescription("Validate the built-in echo endpoint using the REST page handler.");

            TestStep step = new TestStep();
            step.setPage(RestApiPage.NAME);
            step.setAction("GET echo");
            step.setParameters(Map.of(
                    "url", "http://localhost:8080/api/echo",
                    "method", "GET"
            ));

            testCase.setSteps(List.of(step));
            repository.save(testCase);
        }
    }
}
