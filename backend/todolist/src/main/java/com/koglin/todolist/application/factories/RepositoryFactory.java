package com.koglin.todolist.application.factories;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.infra.database.repositories.TaskRepositoryJpa;
import com.koglin.todolist.infra.database.repositories.proxies.TaskRepositoryJpaProxy;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;

public class RepositoryFactory {

    public static TaskRepository makeTaskRepository(EntityManager entityManager) {
        JpaRepositoryFactory factory = new JpaRepositoryFactory(entityManager);
        TaskRepositoryJpa taskRepositoryJpa = factory.getRepository(TaskRepositoryJpa.class);
        return new TaskRepositoryJpaProxy(taskRepositoryJpa);
    }

}
