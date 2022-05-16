package com.koglin.todolist.application.composition;

import com.koglin.todolist.domain.useCases.SaveTask;
import com.koglin.todolist.infra.database.repositories.TaskRepository;
import com.koglin.todolist.infra.gateways.SaveTaskDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SaveTaskFactory {

    @Autowired
    EntityManager entityManager;

    @Bean
    public SaveTask makeSaveTask() {
        TaskRepository repository = RepositoryFactory.makeTaskRepository(entityManager);
        return new SaveTaskDb(repository);
    }

}
