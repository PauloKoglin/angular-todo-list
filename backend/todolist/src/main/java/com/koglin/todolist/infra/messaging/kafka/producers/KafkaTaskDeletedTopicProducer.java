package com.koglin.todolist.infra.messaging.kafka.producers;

import com.koglin.todolist.domain.contracts.gateways.EventDispatcher;
import com.koglin.todolist.domain.events.EventIdentification;
import com.koglin.todolist.domain.events.TaskDeletedEventContent;

public class KafkaTaskDeletedTopicProducer implements EventDispatcher<TaskDeletedEventContent> {

    @Override
    public void dispatch(EventIdentification eventIdentification, TaskDeletedEventContent content) {
        // produce kafka message
    }
}
