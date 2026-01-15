package com.svc_catalog.demo;

import com.svc_catalog.usertask.model.User;
import com.svc_catalog.usertask.store.InMemoryUserStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/demo/insecure")
@ConditionalOnProperty(prefix = "demo.insecure", name = "enabled", havingValue = "true")
public class IdorDemoController {
    private final InMemoryUserStore userStore;

    public IdorDemoController(InMemoryUserStore userStore) {
        this.userStore = userStore;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id) {
        return userStore.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
    }
}
