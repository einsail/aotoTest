package com.example.apitesting.test;

import org.springframework.test.context.ActiveProfilesResolver;

public class TestEnvironmentResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> testClass) {
        String environment = System.getProperty("test.env", "dev");
        return new String[]{environment};
    }
}
