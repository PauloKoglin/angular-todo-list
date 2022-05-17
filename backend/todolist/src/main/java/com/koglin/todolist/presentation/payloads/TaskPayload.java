package com.koglin.todolist.presentation.payloads;

public record TaskPayload (
        Long id,
        String description,
        Boolean done
) { }
