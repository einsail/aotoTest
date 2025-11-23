package com.example.framework;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.framework.model.ExecutionResult;
import com.example.framework.model.ExecutionStatus;
import com.example.framework.model.TestCase;
import com.example.framework.model.TestStep;
import com.example.framework.page.PageActionRegistry;
import com.example.framework.page.RestApiPage;
import com.example.framework.repository.InMemoryTestCaseRepository;
import com.example.framework.service.ExecutionService;
import com.example.framework.service.TestCaseService;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

class ExecutionServiceTest {

    private InMemoryTestCaseRepository repository;
    private RestTemplate restTemplate;
    private MockRestServiceServer server;
    private ExecutionService executionService;

    @BeforeEach
    void setup() {
        repository = new InMemoryTestCaseRepository();
        restTemplate = new RestTemplateBuilder().build();
        server = MockRestServiceServer.createServer(restTemplate);

        PageActionRegistry registry = new PageActionRegistry();
        new RestApiPage(restTemplate, registry);

        TestCaseService caseService = new TestCaseService(repository);
        executionService = new ExecutionService(caseService, registry);
    }

    @Test
    void executesSingleRestStep() {
        TestCase testCase = buildRestTestCase("http://localhost:8080/api/echo");
        repository.save(testCase);

        server.expect(ExpectedCount.once(), requestTo("http://localhost:8080/api/echo"))
                .andExpect(method(org.springframework.http.HttpMethod.GET))
                .andRespond(withSuccess("{\"status\":\"ok\"}", MediaType.APPLICATION_JSON));

        ExecutionResult result = executionService.execute(testCase.getId());

        assertThat(result.getStatus()).isEqualTo(ExecutionStatus.PASS);
        assertThat(result.getSteps()).hasSize(1);
        assertThat(result.getSteps().get(0).getStatusCode()).isEqualTo(200);
        assertThat(result.getSteps().get(0).getResponseBody()).contains("ok");
        server.verify();
    }

    private TestCase buildRestTestCase(String url) {
        TestStep step = new TestStep();
        step.setPage(RestApiPage.NAME);
        step.setAction("GET echo");
        step.setParameters(Map.of(
                "url", url,
                "method", "GET"
        ));

        TestCase testCase = new TestCase();
        testCase.setName("Rest case");
        testCase.setSteps(List.of(step));
        return testCase;
    }
}
