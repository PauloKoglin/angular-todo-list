package com.koglin.todolist.infra.database.repositories.proxies;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.infra.database.entities.TaskEntity;
import com.koglin.todolist.infra.database.repositories.TaskRepositoryJpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskRepositoryJpaProxy implements TaskRepository {

    private final TaskRepositoryJpa taskRepositoryJpa;

    public TaskRepositoryJpaProxy(TaskRepositoryJpa taskRepositoryJpa) {
        super();
        this.taskRepositoryJpa = taskRepositoryJpa;
    }

    @Override
    public List<TaskModel> findAll() {
        return taskRepositoryJpa.findAll()
                .stream()
                .map(this::parseEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskModel> findById(Long id) {
        Optional<TaskModel> result = Optional.empty();
        Optional<TaskEntity> task = taskRepositoryJpa.findById(id);
        if (!task.isEmpty()) {
            result = Optional.of(this.parseEntityToModel(task.get()));
        }
        return result;
    }

    @Override
    public TaskModel save(TaskModel task) {
        final TaskEntity newTask = taskRepositoryJpa.save(this.mapModelToEntity(task));
        return this.parseEntityToModel(newTask);
    }

    @Override
    public void delete(Long id) {
        taskRepositoryJpa.deleteById(id);
    }

    private TaskEntity mapModelToEntity(TaskModel taskModel) {
        return new TaskEntity(
                taskModel.getId(),
                taskModel.getDescription(),
                taskModel.isDone()
        );
    }

    private TaskModel parseEntityToModel(TaskEntity taskEntity) {
        return new TaskModel(
                taskEntity.getId(),
                taskEntity.getDescription(),
                taskEntity.getDone()
        );
    }
}
