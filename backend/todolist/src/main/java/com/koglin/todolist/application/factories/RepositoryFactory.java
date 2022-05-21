package com.koglin.todolist.application.factories;

import com.koglin.todolist.infra.database.repositories.TaskRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;

public class RepositoryFactory {

    public static TaskRepository makeTaskRepository(EntityManager entityManager) {
        JpaRepositoryFactory factory = new JpaRepositoryFactory(entityManager);
        return factory.getRepository(TaskRepository.class);
    }

}
