package com.koglin.todolist.domain.usecases.task;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.exceptions.ModelNotFoundException;
import com.koglin.todolist.domain.models.TaskModel;

import java.util.Optional;

public class FindTaskByIdUseCase {

    private final TaskRepository taskRepository;

    public FindTaskByIdUseCase(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    public TaskModel handle(Long id) {
        Optional<TaskModel> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            throw new ModelNotFoundException(String.format("Task with id \"%d\" was not found", id));
        }
        return task.get();
    }
}
