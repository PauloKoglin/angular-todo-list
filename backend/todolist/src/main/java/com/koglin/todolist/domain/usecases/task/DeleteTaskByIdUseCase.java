package com.koglin.todolist.domain.usecases.task;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.models.TaskModel;

import javax.transaction.Transactional;

public class DeleteTaskByIdUseCase {

    private final TaskRepository taskRepository;
    private final FindTaskByIdUseCase findTaskByIdUseCase;

    public DeleteTaskByIdUseCase(TaskRepository taskRepository, FindTaskByIdUseCase findTaskByIdUseCase) {
        super();
        this.taskRepository = taskRepository;
        this.findTaskByIdUseCase = findTaskByIdUseCase;
    }

    @Transactional
    public TaskModel handle(Long id) {
        TaskModel task = this.findTaskByIdUseCase.handle(id);
        taskRepository.delete(id);
        return task;
    }
}
