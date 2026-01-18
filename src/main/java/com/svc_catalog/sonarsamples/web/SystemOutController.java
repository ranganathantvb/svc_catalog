package com.svc_catalog.sonarsamples.web;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("sonar-samples")
@RequestMapping("/sonar-samples/s106")
public class SystemOutController {
    @GetMapping
    public String ping() {
        System.out.println("S106 sample");
        return "ok";
    }
}
