package com.svc_catalog.demo.insecure;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoInsecureService06 {

    public String invoke() {
        return this.proxiedWork();
    }

    @Transactional
    public String proxiedWork() {
        return "ok";
    }
}
