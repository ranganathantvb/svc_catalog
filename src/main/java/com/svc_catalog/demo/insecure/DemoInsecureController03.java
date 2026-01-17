package com.svc_catalog.demo.insecure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class DemoInsecureController03 {
    @GetMapping("/s6818")
    public String s6818() {
        DemoInsecureService03 service = new DemoInsecureService03();
        return service.message();
    }
}
