package com.example.apitesting.test;

import com.example.apitesting.Application;
import com.example.apitesting.utils.LoggingTestListener;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(resolver = TestEnvironmentResolver.class)
@Listeners(LoggingTestListener.class)
public abstract class BaseApiTest extends AbstractTestNGSpringContextTests {

    @LocalServerPort
    protected int port;

    @Value("${api.default-timeout}")
    private int defaultTimeout;

    protected RequestSpecification requestSpec;

    @BeforeClass(alwaysRun = true)
    public void setUpRequestSpec() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:" + port)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .setConfig(io.restassured.config.RestAssuredConfig.config()
                        .httpClient(io.restassured.config.HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", defaultTimeout)
                                .setParam("http.socket.timeout", defaultTimeout)))
                .build();
    }
}
