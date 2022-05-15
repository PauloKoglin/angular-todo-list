package com.koglin.todolist.infra.gateways;

import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.domain.useCases.SaveTask;
import com.koglin.todolist.infra.database.entities.TaskEntity;
import com.koglin.todolist.infra.database.repositories.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import static org.mockito.Mockito.*;

public class SaveTaskDbTest {

    private TaskRepository taskRepositoryMock;
    private SaveTask sut;

    @BeforeEach
    void beforeEach() {
        this.taskRepositoryMock = mock(TaskRepository.class);
        this.sut = new SaveTaskDb(this.taskRepositoryMock);
    }
    @Test
    void Should_throw_if_empty_description_is_provided() {
        TaskModel task = new TaskModel(1L, "", false);

        Assertions.assertThrows(RuntimeException.class, () -> sut.perform(task));
    }

    @Test
    void Should_call_repository_save_with_correct_params() {
        TaskModel task = new TaskModel(null, "any_value", false);
        TaskEntity expectedInput = new TaskEntity(null, "any_value", false);

        sut.perform(task);

        verify(taskRepositoryMock).save(argThat(arg -> arg.equals(expectedInput)));
    }

}
