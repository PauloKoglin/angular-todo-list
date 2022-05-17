package com.koglin.todolist.domain.models;

import java.util.Objects;

public class TaskModel {
    private Long id;
    private String description;
    private boolean done;

    public TaskModel(Long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskModel taskModel = (TaskModel) o;
        return done == taskModel.done && Objects.equals(id, taskModel.id) && Objects.equals(description, taskModel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
