package com.koglin.todolist.domain.usecases.task;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.models.TaskModel;

import java.util.List;

public class FindAllTasksUseCase {

    private final TaskRepository taskRepository;

    public FindAllTasksUseCase(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> handle() {
        return taskRepository.findAll();
    }

}
