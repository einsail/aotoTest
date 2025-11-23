package com.example.apitesting.utils;

import com.example.apitesting.config.EnvironmentProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

@Component
public class RequestSpecFactory {

    private final EnvironmentProperties environmentProperties;

    public RequestSpecFactory(EnvironmentProperties environmentProperties) {
        this.environmentProperties = environmentProperties;
    }

    public RequestSpecification buildDefaultSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(environmentProperties.getBaseUrl())
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setConfig(RestAssuredConfigFactory.defaultConfig(environmentProperties.getDefaultTimeout()))
                .log(LogDetail.ALL)
                .build();
    }
}
