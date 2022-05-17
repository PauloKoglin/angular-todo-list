package com.koglin.todolist.domain.services;

import com.koglin.todolist.domain.models.TaskModel;

import java.util.List;

public interface TaskService {
    TaskModel perform(TaskModel task);
    List<TaskModel> findAll();
}
