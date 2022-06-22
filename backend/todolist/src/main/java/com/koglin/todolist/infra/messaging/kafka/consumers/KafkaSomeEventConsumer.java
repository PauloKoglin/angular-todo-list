package com.koglin.todolist.infra.messaging.kafka.consumers;

import com.koglin.todolist.domain.usecases.task.SaveTaskUseCase;

public class KafkaSomeEventConsumer {

    private SaveTaskUseCase saveTaskUseCase;

    public KafkaSomeEventConsumer(SaveTaskUseCase saveTaskUseCase) {
        this.saveTaskUseCase = saveTaskUseCase;
    }

    public void consume() {
        // Call some use case that reacts to kafka message
        // this.saveTaskUseCase()
    }
}
