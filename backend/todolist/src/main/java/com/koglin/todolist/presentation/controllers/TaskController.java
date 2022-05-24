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

    public ServerResponse post(HttpRequestAdapter request) {
        TaskPayload payload = request.body(TaskPayload.class);
        final TaskModel newTask = new TaskModel(payload.id(), payload.description(), payload.done());
        final TaskModel createdTask = this.taskService.save(newTask);
        return ServerResponse
                .created(request.buildPostUri(createdTask.getId().toString()))
                .build();
    }

    public ServerResponse put(HttpRequestAdapter request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        TaskPayload payload = request.body(TaskPayload.class);
        final TaskModel newTask = new TaskModel(id, payload.description(), payload.done());
        this.taskService.save(newTask);
        return ServerResponse
                .noContent()
                .build();
    }

    public ServerResponse deleteTask(HttpRequestAdapter request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        taskService.delete(id);
        return ServerResponse
                .noContent()
                .build();
    }
}
