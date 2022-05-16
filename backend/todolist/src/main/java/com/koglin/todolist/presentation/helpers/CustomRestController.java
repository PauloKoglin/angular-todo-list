package com.koglin.todolist.presentation.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.*;

import java.util.function.Function;

public abstract class CustomRestController {
    private final String path;

    public CustomRestController(String path) {
        this.path = path;
    }

    public ServerResponse handle(ServerRequest request, Function<ServerRequest, ServerResponse> handler) {
        try {
            return handler.apply(request);
        } catch (RuntimeException exception) {
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }

    public String getPath() {
        return path;
    }
}
