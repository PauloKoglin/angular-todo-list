package com.koglin.todolist.infra.gateways;

import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.domain.useCases.SaveTask;
import com.koglin.todolist.infra.database.entities.TaskEntity;
import com.koglin.todolist.infra.database.repositories.TaskRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.config.Task;


import static org.mockito.Mockito.*;

public class SaveTaskDbTest {

    private TaskRepository taskRepositoryMock;
    private SaveTask sut;

    @BeforeEach
    void beforeEach() {
        this.taskRepositoryMock = mock(TaskRepository.class);
        when(taskRepositoryMock.save(any(TaskEntity.class))).thenReturn(new TaskEntity(1L, "any_value", false));

        this.sut = new SaveTaskDb(this.taskRepositoryMock);
    }
    @Test
    void Should_throw_if_empty_description_is_provided() {
        TaskModel task = new TaskModel(1L, "", false);

        assertThrows(RuntimeException.class, () -> sut.perform(task));
    }

    @Test
    void Should_call_repository_save_with_correct_params() {
        TaskModel task = new TaskModel(null, "any_value", false);
        TaskEntity expectedInput = new TaskEntity(null, "any_value", false);

        sut.perform(task);

        verify(taskRepositoryMock).save(argThat(arg -> arg.equals(expectedInput)));
    }

    @Test
    void Should_return_saved_task() {
        TaskModel input = new TaskModel(null, "any_value", false);
        when(taskRepositoryMock.save(any(TaskEntity.class))).thenReturn(new TaskEntity(15L, "any_value", false));

        TaskModel output = sut.perform(input);

        final TaskModel expectedOutput = new TaskModel(15L, "any_value", false);
        assertEquals(output, expectedOutput);
    }
}
