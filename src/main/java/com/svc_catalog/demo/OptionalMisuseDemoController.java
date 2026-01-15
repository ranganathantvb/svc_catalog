package com.svc_catalog.demo;

import com.svc_catalog.usertask.model.User;
import com.svc_catalog.usertask.store.InMemoryUserStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class OptionalMisuseDemoController {
    private final InMemoryUserStore userStore;

    public OptionalMisuseDemoController(InMemoryUserStore userStore) {
        this.userStore = userStore;
    }

    @GetMapping("/user-optional/{id}")
    public User getUserOptional(@PathVariable long id) {
        return userStore.get(id).get();
    }
}
