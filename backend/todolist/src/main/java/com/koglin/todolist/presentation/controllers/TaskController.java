package com.koglin.todolist.presentation.controllers;

import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.domain.usecases.task.DeleteTaskByIdUseCase;
import com.koglin.todolist.domain.usecases.task.FindAllTasksUseCase;
import com.koglin.todolist.domain.usecases.task.FindTaskByIdUseCase;
import com.koglin.todolist.domain.usecases.task.SaveTaskUseCase;
import com.koglin.todolist.presentation.adapters.HttpRequestAdapter;
import com.koglin.todolist.presentation.payloads.TaskPayload;
import org.springframework.web.servlet.function.*;

public class TaskController {

    private final FindTaskByIdUseCase findTaskByIdUseCase;
    private final FindAllTasksUseCase findAllTasksUseCase;
    private final SaveTaskUseCase saveTaskUseCase;
    private final DeleteTaskByIdUseCase deleteTaskByIdUseCase;

    public TaskController(
            FindTaskByIdUseCase findTaskByIdUseCase,
            FindAllTasksUseCase findAllTasksUseCase,
            SaveTaskUseCase saveTaskUseCase,
            DeleteTaskByIdUseCase deleteTaskByIdUseCase
    ) {
        super();
        this.findTaskByIdUseCase = findTaskByIdUseCase;
        this.findAllTasksUseCase = findAllTasksUseCase;
        this.saveTaskUseCase = saveTaskUseCase;
        this.deleteTaskByIdUseCase = deleteTaskByIdUseCase;
    }

    public ServerResponse get() {
        return ServerResponse
                .ok()
                .body(this.findAllTasksUseCase.handle());
    }

    public ServerResponse getById(HttpRequestAdapter request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        return ServerResponse
                .ok()
                .body(this.findTaskByIdUseCase.handle(id));
    }

    public ServerResponse post(HttpRequestAdapter request) {
        TaskPayload payload = request.body(TaskPayload.class);
        final TaskModel newTask = new TaskModel(payload.id(), payload.description(), payload.done());
        final TaskModel createdTask = this.saveTaskUseCase.handle(newTask);
        return ServerResponse
                .created(request.buildPostUri(createdTask.getId().toString()))
                .build();
    }

    public ServerResponse put(HttpRequestAdapter request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        TaskPayload payload = request.body(TaskPayload.class);
        final TaskModel newTask = new TaskModel(id, payload.description(), payload.done());
        this.saveTaskUseCase.handle(newTask);
        return ServerResponse
                .noContent()
                .build();
    }

    public ServerResponse delete(HttpRequestAdapter request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        deleteTaskByIdUseCase.handle(id);
        return ServerResponse
                .noContent()
                .build();
    }
}
