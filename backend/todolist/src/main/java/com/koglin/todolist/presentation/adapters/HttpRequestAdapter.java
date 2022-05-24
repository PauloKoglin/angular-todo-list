package com.koglin.todolist.presentation.adapters;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    public String pathVariable(String name) {
        return request.pathVariable(name);
    }

    public URI buildPostUri(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

}
