package com.koglin.todolist.services;

import com.koglin.todolist.models.TaskModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskService {

    @GetMapping("/tasks")
    public TaskModel getTask() {
        return new TaskModel(1, "My task", false);
    }
}
