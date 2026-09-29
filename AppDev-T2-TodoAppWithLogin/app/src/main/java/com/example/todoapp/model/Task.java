package com.example.todoapp.model;

public class Task {

    private int id;
    private final int userId;

    private String title;
    private String notes;

    private TaskStatus status;

    public Task(
            int id,
            int userId,
            String title,
            String notes,
            TaskStatus status) {

        this.id = id;
        this.userId = userId;
        this.title = title;
        this.notes = notes;
        this.status = status;
    }

    public Task(
            int userId,
            String title,
            String notes) {

        this.userId = userId;
        this.title = title;
        this.notes = notes;
        this.status = TaskStatus.PENDING;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void markCompleted() {
        status = TaskStatus.COMPLETED;
    }

    public void markPending() {
        status = TaskStatus.PENDING;
    }

    public boolean isCompleted() {
        return status == TaskStatus.COMPLETED;
    }
}