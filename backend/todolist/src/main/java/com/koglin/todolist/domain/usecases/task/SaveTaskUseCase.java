package com.koglin.todolist.domain.usecases.task;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.models.TaskModel;

import javax.transaction.Transactional;

public class SaveTaskUseCase {

    private final TaskRepository taskRepository;
    public SaveTaskUseCase(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskModel handle(TaskModel task) {
        if (task.getDescription().isEmpty()) {
            throw new RuntimeException("Description must be defined");
        }
        return this.taskRepository.save(task);
    }
}
