package com.koglin.todolist.domain.useCases;

import com.koglin.todolist.domain.models.TaskModel;

import java.util.List;
import java.util.stream.Stream;

public interface SaveTask {
    TaskModel perform(TaskModel task);
    List<TaskModel> findAll();
}
