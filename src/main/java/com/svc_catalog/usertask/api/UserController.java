package com.svc_catalog.usertask.api;

import com.svc_catalog.usertask.dto.CreateUserRequest;
import com.svc_catalog.usertask.dto.UpdateUserRequest;
import com.svc_catalog.usertask.model.User;
import com.svc_catalog.usertask.store.InMemoryUserStore;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final InMemoryUserStore userStore;

    public UserController(InMemoryUserStore userStore) {
        this.userStore = userStore;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody CreateUserRequest req) {
        return userStore.create(req.getName(), req.getEmail());
    }

    @GetMapping
    public List<User> list() {
        return userStore.list();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable long id) {
        return userStore.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
    }

    @PutMapping("/{id}")
    public User update(@PathVariable long id, @Valid @RequestBody UpdateUserRequest req) {
        return userStore.update(id, req.getName(), req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        boolean deleted = userStore.delete(id);
        if (!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id);
    }
}
