package com.koglin.todolist.presentation.helpers;

import com.koglin.todolist.presentation.adapters.HttpRequestAdapter;
import com.koglin.todolist.presentation.payloads.ErrorResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.*;
import java.util.function.Function;

public abstract class CustomRequestHandler {

    public static ServerResponse handle(ServerRequest request, Function<HttpRequestAdapter, ServerResponse> handler) {
        try {
            return handler.apply(HttpRequestAdapter.adapt(request));
        } catch (RuntimeException exception) {
            ErrorResponseBody errorBody = new ErrorResponseBody(
                    System.currentTimeMillis(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.name(),
                    exception.getMessage(),
                    request.path()
            );
            return ServerResponse.status(errorBody.status()).body(errorBody);
        }
    }

    public static ServerResponse handle(ServerRequest request, NoArgsRequest<ServerResponse> handler) {
        try {
            return handler.perform();
        } catch (RuntimeException exception) {
            ErrorResponseBody errorBody = new ErrorResponseBody(
                    System.currentTimeMillis(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.name(),
                    exception.getMessage(),
                    request.path()
            );
            return ServerResponse.status(errorBody.status()).body(errorBody);
        }
    }
}
