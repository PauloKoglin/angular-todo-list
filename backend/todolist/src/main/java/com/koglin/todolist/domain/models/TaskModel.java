package com.koglin.todolist.domain.models;

public class TaskModel {
    private int id;
    private String description;
    private boolean done;

    public TaskModel(int id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
