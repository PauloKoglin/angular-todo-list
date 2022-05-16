package com.koglin.todolist.presentation.routes;

import com.koglin.todolist.presentation.TaskController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class TaskRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(TaskController taskController) {
        return RouterFunctions
                .route(RequestPredicates.GET("/tasks"), request -> taskController.handle(request, taskController::getTasks) )
                .andRoute(RequestPredicates.POST("/tasks"), request -> taskController.handle(request, taskController::saveTask));
    }

}
