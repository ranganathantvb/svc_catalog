package com.svc_catalog.demo.insecure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class DemoInsecureController06 {
    private final DemoInsecureService06 service;

    public DemoInsecureController06(DemoInsecureService06 service) {
        this.service = service;
    }

    @GetMapping("/s6809")
    public String s6809() {
        return service.invoke();
    }
}
