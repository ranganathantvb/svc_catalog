package com.svc_catalog.usertask.api;

import com.svc_catalog.usertask.dto.CreateTaskRequest;
import com.svc_catalog.usertask.dto.UpdateTaskRequest;
import com.svc_catalog.usertask.model.Task;
import com.svc_catalog.usertask.store.InMemoryTaskStore;
import com.svc_catalog.usertask.store.InMemoryUserStore;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final InMemoryTaskStore taskStore;
    private final InMemoryUserStore userStore;

    public TaskController(InMemoryTaskStore taskStore, InMemoryUserStore userStore) {
        this.taskStore = taskStore;
        this.userStore = userStore;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody CreateTaskRequest req) {
        if (!userStore.exists(req.getAssigneeUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Assignee userId does not exist: " + req.getAssigneeUserId());
        }
        return taskStore.create(req.getTitle(), false, req.getAssigneeUserId());
    }

    @GetMapping
    public List<Task> list(@RequestParam(required = false) Long assigneeUserId) {
        return taskStore.list(Optional.ofNullable(assigneeUserId));
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable long id) {
        return taskStore.get(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found: " + id));
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable long id, @Valid @RequestBody UpdateTaskRequest req) {
        if (!userStore.exists(req.getAssigneeUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Assignee userId does not exist: " + req.getAssigneeUserId());
        }
        return taskStore.update(id, req.getTitle(), req.isCompleted(), req.getAssigneeUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found: " + id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        boolean deleted = taskStore.delete(id);
        if (!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found: " + id);
    }
}
