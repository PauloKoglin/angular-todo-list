package com.koglin.todolist.application.factories;

import com.koglin.todolist.domain.services.TaskService;
import com.koglin.todolist.infra.database.repositories.TaskRepository;
import com.koglin.todolist.infra.gateways.TaskServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SaveTaskFactory {

    @Autowired
    EntityManager entityManager;

    @Bean
    public TaskService makeSaveTask() {
        TaskRepository repository = RepositoryFactory.makeTaskRepository(entityManager);
        return new TaskServiceDb(repository);
    }

}
