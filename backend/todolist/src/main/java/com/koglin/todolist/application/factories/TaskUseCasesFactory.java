package com.koglin.todolist.application.factories;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.usecases.task.DeleteTaskByIdUseCase;
import com.koglin.todolist.domain.usecases.task.FindAllTasksUseCase;
import com.koglin.todolist.domain.usecases.task.FindTaskByIdUseCase;
import com.koglin.todolist.domain.usecases.task.SaveTaskUseCase;
import com.koglin.todolist.infra.messaging.kafka.KafkaTaskDeletedTopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class TaskUseCasesFactory {

    @Autowired
    EntityManager entityManager;

    private final TaskRepository repository;

    public TaskUseCasesFactory(EntityManager entityManager) {
        this.repository = RepositoryFactory.makeTaskRepository(entityManager);

    }

    @Bean
    public FindTaskByIdUseCase makeFindTaskByIdUseCase() {
        return new FindTaskByIdUseCase(repository);
    }

    @Bean
    public FindAllTasksUseCase makeFindAllTasksUseCase() {
        return new FindAllTasksUseCase(repository);
    }

    @Bean
    public SaveTaskUseCase makeSaveTaskUseCase() {
        return new SaveTaskUseCase(repository);
    }

    @Bean
    public DeleteTaskByIdUseCase makeDeleteTaskByIdUseCase() {
        return new DeleteTaskByIdUseCase(
            repository,
            this.makeFindTaskByIdUseCase(),
            new KafkaTaskDeletedTopicProducer()
        );
    }

}
