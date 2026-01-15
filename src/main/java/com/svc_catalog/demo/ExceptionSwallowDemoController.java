package com.svc_catalog.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class ExceptionSwallowDemoController {

    @GetMapping("/swallow")
    public String swallow(@RequestParam String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception ignored) {
        }
        return "ok";
    }
}
