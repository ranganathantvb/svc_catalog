package com.svc_catalog.usertask.model;

public class Task {
    private Long id;
    private String title;
    private boolean completed;
    private Long assigneeUserId;

    public Task() {}

    public Task(Long id, String title, boolean completed, Long assigneeUserId) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.assigneeUserId = assigneeUserId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public Long getAssigneeUserId() { return assigneeUserId; }
    public void setAssigneeUserId(Long assigneeUserId) { this.assigneeUserId = assigneeUserId; }
}
