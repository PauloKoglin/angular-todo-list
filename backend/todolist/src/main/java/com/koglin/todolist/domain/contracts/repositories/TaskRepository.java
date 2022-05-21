package com.koglin.todolist.domain.contracts.repositories;

import com.koglin.todolist.domain.models.TaskModel;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<TaskModel> findAll();

    Optional<TaskModel> findById(Long id);

    TaskModel save(TaskModel task);

    void delete(Long id);
}
