package com.koglin.todolist.domain.services;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.models.TaskModel;

import javax.transaction.Transactional;
import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskModel save(TaskModel task) {
        if (task.getDescription().isEmpty()) {
            throw new RuntimeException("Description must be defined");
        }
        return this.taskRepository.save(task);
    }

    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }
}
