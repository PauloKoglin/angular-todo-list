package com.koglin.todolist.infra.gateways;

import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.domain.useCases.SaveTask;
import com.koglin.todolist.infra.database.entities.TaskEntity;
import com.koglin.todolist.infra.database.repositories.TaskRepository;

public class SaveTaskDb implements SaveTask {
    private final TaskRepository taskRepository;
    public SaveTaskDb(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    @Override
    public void perform(TaskModel task) {
        if (task.getDescription().isEmpty()) {
            throw new RuntimeException("Description must be defined");
        }
        this.taskRepository.save(this.parseModelToEntity(task));
    }

    private TaskEntity parseModelToEntity(TaskModel taskModel) {
        return new TaskEntity(
                taskModel.getId(),
                taskModel.getDescription(),
                taskModel.isDone()
        );
    }
}
