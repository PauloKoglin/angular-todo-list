package com.koglin.todolist.domain.usecases.task;

import com.koglin.todolist.domain.contracts.gateways.EventDispatcher;
import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.events.EventIdentification;
import com.koglin.todolist.domain.events.TaskDeletedEventContent;
import com.koglin.todolist.domain.models.TaskModel;

import javax.transaction.Transactional;

public class DeleteTaskByIdUseCase {

    private final TaskRepository taskRepository;
    private final FindTaskByIdUseCase findTaskByIdUseCase;
    private final EventDispatcher<TaskDeletedEventContent> eventDispatcher;

    public DeleteTaskByIdUseCase(
        TaskRepository taskRepository,
        FindTaskByIdUseCase findTaskByIdUseCase,
        EventDispatcher<TaskDeletedEventContent> eventDispatcher
    ) {
        super();
        this.taskRepository = taskRepository;
        this.findTaskByIdUseCase = findTaskByIdUseCase;
        this.eventDispatcher = eventDispatcher;
    }

    @Transactional
    public TaskModel handle(Long id) {
        TaskModel task = this.findTaskByIdUseCase.handle(id);
        taskRepository.delete(id);
        eventDispatcher.dispatch(
            EventIdentification.TASK_DELETED,
            new TaskDeletedEventContent(task.getId(), task.getDescription())
        );
        return task;
    }
}
