package com.koglin.todolist.presentation.routes;

import com.koglin.todolist.presentation.controllers.TaskController;
import com.koglin.todolist.presentation.helpers.CustomRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.servlet.function.RequestPredicates.*;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class TaskRouter {

    private static final String taskPath = "/tasks";

    @Bean
    public RouterFunction<ServerResponse> routes(TaskController taskController) {
        return RouterFunctions
                .route(GET(taskPath), request -> CustomRequestHandler.handle(request, taskController::get))
                .andRoute(GET(taskPath + "/{id}"), request -> CustomRequestHandler.handle(request, taskController::getById))
                .andRoute(POST(taskPath), request -> CustomRequestHandler.handle(request, taskController::post))
                .andRoute(PUT(taskPath + "/{id}"), request -> CustomRequestHandler.handle(request, taskController::put))
                .andRoute(DELETE(taskPath + "/{id}"), request -> CustomRequestHandler.handle(request, taskController::delete));
    }
}
