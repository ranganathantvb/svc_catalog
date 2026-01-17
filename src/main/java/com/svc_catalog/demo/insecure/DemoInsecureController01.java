package com.svc_catalog.demo.insecure;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class DemoInsecureController01 {

    @GetMapping("/s2095")
    public String s2095() throws IOException {
        FileInputStream inputStream = new FileInputStream(System.getProperty("java.io.tmpdir") + "/demo-s2095.txt");
        int firstByte = inputStream.read();
        return "ok:" + firstByte;
    }
}
