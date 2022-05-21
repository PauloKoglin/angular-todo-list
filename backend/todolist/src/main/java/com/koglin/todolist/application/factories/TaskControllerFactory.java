package com.koglin.todolist.application.factories;

import com.koglin.todolist.domain.contracts.services.TaskService;
import com.koglin.todolist.presentation.controllers.TaskController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskControllerFactory {

    @Bean
    public TaskController makeTaskController(SaveTaskFactory saveTaskFactory) {
        TaskService service = saveTaskFactory.makeSaveTask();
        return new TaskController(service);
    }
}
