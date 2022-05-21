package com.koglin.todolist.domain.contracts.repositories;

import com.koglin.todolist.domain.models.TaskModel;

import java.util.List;

public interface TaskRepository {

    List<TaskModel> findAll();

    TaskModel save(TaskModel task);
}
