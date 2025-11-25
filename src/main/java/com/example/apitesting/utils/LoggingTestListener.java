package com.example.apitesting.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class LoggingTestListener implements ITestListener, ISuiteListener {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingTestListener.class);

    @Override
    public void onStart(ISuite suite) {
        LOG.info("==== Test suite {} started ====", suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        LOG.info("==== Test suite {} finished ====", suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("Starting test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("Test succeeded: {} ({} ms)", result.getName(), result.getEndMillis() - result.getStartMillis());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.error("Test failed: {}", result.getName(), result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOG.warn("Test skipped: {}", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        LOG.info("Starting test context: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LOG.info("Finished test context: {}", context.getName());
    }
}
