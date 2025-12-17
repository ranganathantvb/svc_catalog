package com.svc_catalog.usertask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTaskRequest {
    @NotBlank
    private String title;

    @NotNull
    private Long assigneeUserId;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getAssigneeUserId() { return assigneeUserId; }
    public void setAssigneeUserId(Long assigneeUserId) { this.assigneeUserId = assigneeUserId; }
}
