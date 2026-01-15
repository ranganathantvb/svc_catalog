package com.svc_catalog.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class InsecureSecretDemoController {
    private static final String DEMO_API_TOKEN = "sk_live_1234567890abcdef";

    @GetMapping("/hardcoded-secret")
    public String hardcodedSecret() {
        return "Using token length: " + DEMO_API_TOKEN.length();
    }
}
