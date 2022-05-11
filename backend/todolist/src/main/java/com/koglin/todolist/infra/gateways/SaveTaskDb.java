package com.koglin.todolist.infra.gateways;

import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.domain.useCases.SaveTask;

public class SaveTaskDb implements SaveTask {

    @Override
    public void perform(TaskModel task) {
        if (task.getDescription().isEmpty()) {
            throw new RuntimeException("Description must be defined");
        }
    }
}
