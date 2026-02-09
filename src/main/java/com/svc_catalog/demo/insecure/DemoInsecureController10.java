package com.svc_catalog.demo.insecure;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class DemoInsecureController10 {

    @GetMapping("/s5445")
    public String s5445() throws IOException {
        File tempFile = new File("/mySecureDirectory/" + System.currentTimeMillis() + ".tmp");
        return "ok:" + tempFile.getAbsolutePath();
    }
}
