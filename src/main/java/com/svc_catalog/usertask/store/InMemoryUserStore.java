package com.svc_catalog.usertask.store;

import com.svc_catalog.usertask.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryUserStore {
    private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(0);

    public User create(String name, String email) {
        long id = idSeq.incrementAndGet();
        User u = new User(id, name, email);
        users.put(id, u);
        return u;
    }

    public List<User> list() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> update(long id, String name, String email) {
        return Optional.ofNullable(users.computeIfPresent(id, (k, old) -> new User(k, name, email)));
    }

    public boolean delete(long id) {
        return users.remove(id) != null;
    }

    public boolean exists(long id) {
        return users.containsKey(id);
    }
}
