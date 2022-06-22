package com.koglin.todolist.domain.contracts.gateways;

import com.koglin.todolist.domain.events.EventIdentification;

public interface EventDispatcher<CONTENT_TYPE> {
    void dispatch(EventIdentification eventIdentification, CONTENT_TYPE content);
}
