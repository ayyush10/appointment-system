package com.example.appointmentsystem.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestLogger {
    public void logTestStep(String step) {
        String separator = "=".repeat(80);
        log.info("\n{}\nTEST STEP: {}\n{}", separator, step, separator);
    }

    public void logTestResult(String step, boolean success) {
        String result = success ? "✓ PASSED" : "✗ FAILED";
        String separator = "-".repeat(80);
        log.info("\n{}\n{}: {}\n{}", separator, step, result, separator);
    }
}