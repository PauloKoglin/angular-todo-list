package com.koglin.todolist.presentation.payloads;

public record ErrorResponseBody (
        Long timestamp,
        int status,
        String error,
        String message,
        String path
) {}
