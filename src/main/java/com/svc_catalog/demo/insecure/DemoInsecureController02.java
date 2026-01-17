package com.svc_catalog.demo.insecure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class DemoInsecureController02 {
    private final DemoInsecureService02 service;

    public DemoInsecureController02(DemoInsecureService02 service) {
        this.service = service;
    }

    @GetMapping("/s1186")
    public String s1186() {
        service.emptyOperation();
        return "ok";
    }
}
