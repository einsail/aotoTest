package com.example.framework.page;

import com.example.framework.model.ExecutionStatus;
import com.example.framework.model.StepResult;
import com.example.framework.model.TestStep;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestApiPage implements PageActionHandler {

    public static final String NAME = "rest";

    private final RestTemplate restTemplate;

    public RestApiPage(RestTemplate restTemplate, PageActionRegistry registry) {
        this.restTemplate = restTemplate;
        registry.register(this);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public StepResult execute(TestStep step) {
        StepResult result = new StepResult();
        result.setAction(step.getAction());
        result.setPage(step.getPage());

        try {
            Map<String, Object> params = step.getParameters();
            String url = (String) params.get("url");
            String method = (String) params.getOrDefault("method", "GET");
            Object body = params.get("body");

            HttpHeaders headers = new HttpHeaders();
            Object headerMap = params.get("headers");
            if (headerMap instanceof Map<?, ?> provided) {
                provided.forEach((key, value) -> headers.add(String.valueOf(key), String.valueOf(value)));
            }

            HttpEntity<Object> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.valueOf(method.toUpperCase()), entity, String.class);

            result.setResponseBody(response.getBody());
            result.setStatusCode(response.getStatusCode().value());
            result.setStatus(ExecutionStatus.PASS);
            result.getMetadata().put("url", url);
            result.getMetadata().put("method", method);
        } catch (Exception e) {
            result.setStatus(ExecutionStatus.FAIL);
            result.setErrorMessage(e.getMessage());
        }

        return result;
    }
}
