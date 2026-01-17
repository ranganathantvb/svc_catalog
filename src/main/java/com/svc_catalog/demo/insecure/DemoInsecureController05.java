package com.svc_catalog.demo.insecure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
@Profile("demo")
public class DemoInsecureController05 {

    @Value("${demo.insecure.missing}")
    private String insecureConfig;

    @GetMapping("/s6816")
    public String s6816() {
        return "ok:" + insecureConfig;
    }
}
