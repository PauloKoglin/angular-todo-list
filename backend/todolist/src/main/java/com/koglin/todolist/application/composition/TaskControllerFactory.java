package com.koglin.todolist.application.composition;

import com.koglin.todolist.domain.useCases.SaveTask;
import com.koglin.todolist.presentation.controllers.TaskController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskControllerFactory {

    @Bean
    public TaskController makeTaskController(SaveTaskFactory saveTaskFactory) {
        SaveTask service = saveTaskFactory.makeSaveTask();
        return new TaskController(service);
    }
}
