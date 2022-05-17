package com.koglin.todolist.presentation.controllers;

import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.domain.useCases.SaveTask;
import com.koglin.todolist.presentation.adapters.HttpRequestAdapter;
import com.koglin.todolist.presentation.payloads.TaskPayload;
import org.springframework.web.servlet.function.*;

import java.util.List;

public class TaskController {

    private final SaveTask saveTask;

    public TaskController(SaveTask saveTask) {
        super();
        this.saveTask = saveTask;
    }

    public ServerResponse getTasks(HttpRequestAdapter ignoredRequest) {
        List<TaskModel> tasks = this.saveTask.findAll();
        return ServerResponse
                .ok()
                .body(tasks);
    }

    public ServerResponse saveTask(HttpRequestAdapter request) {
        TaskPayload payload = request.body(TaskPayload.class);
        final TaskModel newTask = new TaskModel(payload.id(), payload.description(), payload.done());
        TaskModel savedTask = this.saveTask.perform(newTask);
        return ServerResponse
                .ok()
                .body(savedTask);
    }
}
