package com.svc_catalog.demo.insecure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class DemoInsecureController04 {

    @GetMapping("/s6814")
    public String s6814(@RequestParam(required = false) int page) {
        return "ok:" + page;
    }
}
