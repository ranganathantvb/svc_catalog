package com.svc_catalog.sonarsamples.web;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("sonar-samples")
@RequestMapping("/sonar-samples/s4507")
public class PrintStackTraceController {
    @GetMapping
    public String trigger() {
        try {
            throw new IllegalStateException("Sample error");
        } catch (Exception e) {
            // Sensitive
            return "error";
        }
    }
}
