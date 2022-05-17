package com.koglin.todolist.presentation.helpers;

import com.koglin.todolist.presentation.adapters.HttpRequestAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.*;
import java.util.function.Function;

public abstract class RequestHandler {

    public static ServerResponse handle(ServerRequest request, Function<HttpRequestAdapter, ServerResponse> handler) {
        try {
            return handler.apply(HttpRequestAdapter.adapt(request));
        } catch (RuntimeException exception) {
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error:" + exception.getMessage());
        }
    }
}
