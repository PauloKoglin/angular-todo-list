package com.koglin.todolist.domain.events;

public enum EventIdentification {

    TASK_DELETED("task-deleted"),
    TASK_CREATED("task-created");

    private final String event;

    EventIdentification(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return this.event;
    }
}
