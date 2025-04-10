package org.example.todothymeleaf.model;

public class Task {
    private Long id;
    private String description;
    private boolean status;

    public Task() {
    }

    public Task(Long id, String description, boolean status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusAsText() {
        return status ? "Yes" : "No";
    }
}