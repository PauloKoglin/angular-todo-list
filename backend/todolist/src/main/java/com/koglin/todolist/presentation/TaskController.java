package com.koglin.todolist.presentation;

import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.domain.useCases.SaveTask;
import com.koglin.todolist.presentation.helpers.CustomRestController;
import com.koglin.todolist.presentation.payloads.TaskPayload;
import org.springframework.web.servlet.function.*;

import java.util.List;

public class TaskController extends CustomRestController {

    private final SaveTask saveTask;

    public TaskController(SaveTask saveTask) {
        super("/tasks");
        this.saveTask = saveTask;
    }


    public ServerResponse getTasks(ServerRequest ignoredRequest) {
        List<TaskModel> tasks = this.saveTask.findAll();
        return ServerResponse
                .ok()
                .body(tasks);
    }

    public ServerResponse saveTask(ServerRequest request) {
        TaskPayload payload;
        // TODO: remove try catch
        try {
            payload = request.body(TaskPayload.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final TaskModel newTask = new TaskModel(payload.getId(), payload.getDescription(), payload.getDone());
        TaskModel savedTask = this.saveTask.perform(newTask);
        return ServerResponse
                .ok()
                .body(savedTask);
    }
}
