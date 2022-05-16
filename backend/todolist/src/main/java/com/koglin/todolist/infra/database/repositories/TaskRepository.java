package com.koglin.todolist.infra.database.repositories;

import com.koglin.todolist.infra.database.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
