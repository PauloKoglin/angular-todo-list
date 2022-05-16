package com.koglin.todolist.infra.gateways;

import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.domain.useCases.SaveTask;
import com.koglin.todolist.infra.database.entities.TaskEntity;
import com.koglin.todolist.infra.database.repositories.TaskRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class SaveTaskDb implements SaveTask {
    private final TaskRepository taskRepository;
    public SaveTaskDb(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public TaskModel perform(TaskModel task) {
        if (task.getDescription().isEmpty()) {
            throw new RuntimeException("Description must be defined");
        }
        TaskEntity newTask = this.taskRepository.save(this.parseModelToEntity(task));

        task.setId(newTask.getId());

        return task;
    }

    @Override
    public List<TaskModel> findAll() {
        return taskRepository.findAll().stream().map(
                task -> new TaskModel(task.getId(), task.getDescription(), task.getDone())
        ).collect(Collectors.toList());
    }

    private TaskEntity parseModelToEntity(TaskModel taskModel) {
        return new TaskEntity(
                taskModel.getId(),
                taskModel.getDescription(),
                taskModel.isDone()
        );
    }
}
