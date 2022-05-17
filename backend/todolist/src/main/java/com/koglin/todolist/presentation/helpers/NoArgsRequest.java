package com.koglin.todolist.presentation.helpers;

@FunctionalInterface
public interface NoArgsRequest<R> {
    R perform();
}
