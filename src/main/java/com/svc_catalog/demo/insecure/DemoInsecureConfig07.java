package com.svc_catalog.demo.insecure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

@Configuration
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class DemoInsecureConfig07 {

    @Async
    public void runAsync() {
    }
}
