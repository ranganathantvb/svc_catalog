package com.svc_catalog.demo.insecure;

import org.springframework.beans.factory.annotation.Autowired;

public class DemoInsecureService03 {

    @Autowired
    public DemoInsecureService03() {
    }

    @Autowired
    public DemoInsecureService03(DemoInsecureService03 dependency) {
    }

    public String message() {
        return "ok";
    }
}
