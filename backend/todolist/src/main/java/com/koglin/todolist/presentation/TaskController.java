package com.koglin.todolist.presentation;

import com.koglin.todolist.domain.models.TaskModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping("/tasks")
    public TaskModel getTask() {
        return new TaskModel(1, "My task", false);
    }
}
