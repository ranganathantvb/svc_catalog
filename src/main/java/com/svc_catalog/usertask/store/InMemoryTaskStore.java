package com.svc_catalog.usertask.store;

import com.svc_catalog.usertask.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class InMemoryTaskStore {
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(0);

    public Task create(String title, boolean completed, long assigneeUserId) {
        long id = idSeq.incrementAndGet();
        Task t = new Task(id, title, completed, assigneeUserId);
        tasks.put(id, t);
        return t;
    }

    public List<Task> list(Optional<Long> assigneeUserId) {
        if (assigneeUserId.isEmpty()) return new ArrayList<>(tasks.values());
        long uid = assigneeUserId.get();
        return tasks.values().stream()
                .filter(t -> t.getAssigneeUserId() != null && t.getAssigneeUserId() == uid)
                .collect(Collectors.toList());
    }

    public Optional<Task> get(long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public Optional<Task> update(long id, String title, boolean completed, long assigneeUserId) {
        return Optional.ofNullable(tasks.computeIfPresent(id, (k, old) -> new Task(k, title, completed, assigneeUserId)));
    }

    public boolean delete(long id) {
        return tasks.remove(id) != null;
    }
}
