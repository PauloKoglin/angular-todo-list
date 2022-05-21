package com.koglin.todolist.domain.services;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.models.TaskModel;

import javax.transaction.Transactional;
import java.util.List;

public class TaskService implements com.koglin.todolist.domain.contracts.services.TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public TaskModel perform(TaskModel task) {
        if (task.getDescription().isEmpty()) {
            throw new RuntimeException("Description must be defined");
        }
        return this.taskRepository.save(task);
    }

    @Override
    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }
}
