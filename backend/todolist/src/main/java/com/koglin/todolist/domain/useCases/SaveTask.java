package com.koglin.todolist.domain.useCases;

import com.koglin.todolist.domain.models.TaskModel;

public interface SaveTask {
    void perform(TaskModel task);
}
