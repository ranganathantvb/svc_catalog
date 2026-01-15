package com.svc_catalog.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class SensitiveLoggingDemoController {
    private static final Logger log = LoggerFactory.getLogger(SensitiveLoggingDemoController.class);

    @GetMapping("/log-auth")
    public String logAuthorization(@RequestHeader("Authorization") String authorization) {
        log.info("Authorization header received: {}", authorization);
        return "logged";
    }
}
