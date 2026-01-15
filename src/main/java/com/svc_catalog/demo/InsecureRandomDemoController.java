package com.svc_catalog.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Random;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class InsecureRandomDemoController {

    @GetMapping("/insecure-random")
    public String insecureRandomToken() {
        byte[] bytes = new byte[16];
        new Random().nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
