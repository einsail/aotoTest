package com.example.apitesting.test;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class HealthApiTest extends BaseApiTest {

    @Test(description = "Health endpoint returns UP")
    public void healthEndpointIsUp() {
        requestSpec
                .when()
                .get("/api/health")
                .then()
                .statusCode(200)
                .body(equalTo("UP"));
    }
}
