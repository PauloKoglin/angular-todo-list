package com.koglin.todolist.domain.events;

public record TaskDeletedEventContent(Long taskId, String description) {
}
