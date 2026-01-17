package com.svc_catalog.demo.insecure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class DemoInsecureController07 {
    private final DemoInsecureConfig07 config;

    public DemoInsecureController07(DemoInsecureConfig07 config) {
        this.config = config;
    }

    @GetMapping("/s6817")
    public String s6817() {
        config.runAsync();
        return "ok";
    }
}
