package com.koglin.todolist.presentation.adapters;

import org.springframework.web.servlet.function.ServerRequest;

public class HttpRequestAdapter {

    private final ServerRequest request;

    public HttpRequestAdapter(ServerRequest request) {
        this.request = request;
    }

    public static HttpRequestAdapter adapt(ServerRequest request) {
        return new HttpRequestAdapter(request);
    }

    public <T> T body(Class<T> bodyType) {
        try {
            return request.body(bodyType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
