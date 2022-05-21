package com.koglin.todolist.presentation.controllers;

import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.domain.services.TaskService;
import com.koglin.todolist.presentation.adapters.HttpRequestAdapter;
import com.koglin.todolist.presentation.payloads.TaskPayload;
import org.springframework.web.servlet.function.*;

public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        super();
        this.taskService = taskService;
    }

    public ServerResponse getTasks() {
        return ServerResponse
                .ok()
                .body(this.taskService.findAll());
    }

    public ServerResponse saveTask(HttpRequestAdapter request) {
        TaskPayload payload = request.body(TaskPayload.class);
        final TaskModel newTask = new TaskModel(payload.id(), payload.description(), payload.done());
        return ServerResponse
                .ok()
                .body(this.taskService.save(newTask));
    }

    public ServerResponse deleteTask(HttpRequestAdapter request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        TaskModel removedTask = taskService.delete(id);
        return ServerResponse
                .ok()
                .body(removedTask);
    }
}
