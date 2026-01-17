package com.svc_catalog.demo.insecure;

import javax.xml.transform.TransformerFactory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class DemoInsecureController09 {

    @GetMapping("/s6373")
    public String s6373() throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.newTransformer();
        return "ok";
    }
}
