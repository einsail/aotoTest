package com.example.apitesting.utils;

import io.restassured.config.ConnectionConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RestAssuredConfigFactory {

    private static final Logger LOG = LoggerFactory.getLogger(RestAssuredConfigFactory.class);

    private RestAssuredConfigFactory() {
    }

    public static RestAssuredConfig defaultConfig(int timeoutMillis) {
        LOG.info("Building RestAssured configuration with timeout {} ms", timeoutMillis);
        return RestAssuredConfig.config()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", timeoutMillis)
                        .setParam("http.socket.timeout", timeoutMillis))
                .connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
    }
}
