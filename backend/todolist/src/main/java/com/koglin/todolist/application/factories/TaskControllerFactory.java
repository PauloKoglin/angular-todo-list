package com.koglin.todolist.application.factories;

import com.koglin.todolist.presentation.controllers.TaskController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskControllerFactory {

    @Bean
    public TaskController makeTaskController(TaskUseCasesFactory taskUseCasesFactory) {
        return new TaskController(
                taskUseCasesFactory.makeFindTaskByIdUseCase(),
                taskUseCasesFactory.makeFindAllTasksUseCase(),
                taskUseCasesFactory.makeSaveTaskUseCase(),
                taskUseCasesFactory.makeDeleteTaskByIdUseCase()
        );
    }
}
