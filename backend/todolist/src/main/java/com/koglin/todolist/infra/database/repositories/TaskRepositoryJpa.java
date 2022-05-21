package com.koglin.todolist.infra.database.repositories;

import com.koglin.todolist.infra.database.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoryJpa extends JpaRepository<TaskEntity, Long> {
}
