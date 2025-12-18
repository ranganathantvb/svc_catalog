package com.svc_catalog.usertask.api;

import com.svc_catalog.usertask.dto.CreateUserRequest;
import com.svc_catalog.usertask.dto.UpdateUserRequest;
import com.svc_catalog.usertask.model.User;
import com.svc_catalog.usertask.store.InMemoryUserStore;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Value;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final InMemoryUserStore userStore;
    @Value("${demo.insecure.enabled:false}")
    private boolean insecureDemoEnabled;

    public UserController(InMemoryUserStore userStore) {
        this.userStore = userStore;
    }

        /**
     * DEMO ONLY – SECURITY ISSUE
     *
     * Mass Assignment / Over-posting vulnerability.
     * Domain object is bound directly from the request body.
     * Client controls fields that should never be client-controlled.
     */
    @PostMapping("/demo/bad-bind")
    public User createUserBadBinding(@RequestBody User user) {

        if (!insecureDemoEnabled) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // ❌ trusting client-provided object
        return userStore.create(user.getName(), user.getEmail());
    }
 
    /**
     * DEMO ONLY – SECURITY ISSUE
     *
     * Broken Object Level Authorization (IDOR).
     * Any caller can fetch any user by ID.
     */
    @GetMapping("/demo/{id}")
    public User getAnyUser(@PathVariable long id) {

        if (!insecureDemoEnabled) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // ❌ no authentication / authorization check
        return userStore.get(id).orElseThrow();
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


     /**
     * DEMO ONLY – SECURITY ISSUE
     *
     * Leaks internal exception details to client.
     */
    @GetMapping("/demo/error")
    public User leakError() {

        if (!insecureDemoEnabled) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        try {
            throw new IllegalStateException("Internal user service failure");
        } catch (Exception e) {
            // ❌ leaking internals
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }


    /**
     * DEMO ONLY – BUG
     *
     * No validation – accepts empty or invalid fields.
     */
    @PostMapping("/demo/no-validation")
    public User createWithoutValidation(@RequestBody CreateUserRequest req) {

        if (!insecureDemoEnabled) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // ❌ @Valid intentionally omitted
        return userStore.create(req.getName(), req.getEmail());
    }


    /**
     * DEMO ONLY – SECURITY HOTSPOT
     *
     * Unbounded data exposure – potential performance / DoS issue.
     */
    @GetMapping("/demo/all")
    public List<User> listAllUsersUnbounded() {

        if (!insecureDemoEnabled) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // ❌ no pagination / limit
        return userStore.list();
    }

    /**
     * DEMO ONLY – SECURITY HOTSPOT
     * Weak cryptographic algorithm (MD5).
     */
    @GetMapping("/demo/weak-hash")
    public String weakHash(@RequestParam String input) throws Exception {
        if (!insecureDemoEnabled) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        MessageDigest md = MessageDigest.getInstance("MD5"); // Sonar usually flags this
        byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(digest);
    }

    /**
     * DEMO ONLY – SECURITY HOTSPOT
     * Insecure randomness: java.util.Random is not suitable for security use cases.
     */
    @GetMapping("/demo/insecure-random")
    public int insecureRandom() {
        if (!insecureDemoEnabled) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new Random().nextInt(); // Sonar often flags in security contexts
    }


    /**
     * DEMO ONLY – SECURITY HOTSPOT
     * Insecure deserialization API usage (ObjectInputStream).
     */
    @PostMapping("/demo/deserialize")
    public String insecureDeserialize(@RequestBody byte[] data) throws Exception {
        if (!insecureDemoEnabled) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            Object obj = ois.readObject(); // Sonar frequently flags this
            return "DEMO ONLY: deserialized type=" + obj.getClass().getName();
        }
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
