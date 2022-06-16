package com.koglin.todolist.domain.usecases.task;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.exceptions.ModelNotFoundException;
import com.koglin.todolist.domain.models.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class FindTaskByIdUseCaseTest {

    private TaskRepository taskRepositoryMock;
    private FindTaskByIdUseCase sut;

    @BeforeEach
    void beforeEach() {
        this.taskRepositoryMock = mock(TaskRepository.class);

        this.sut = new FindTaskByIdUseCase(this.taskRepositoryMock);
    }

    @Test
    void should_call_repository_once() {
        final Long input = 10L;
        when(taskRepositoryMock.findById(any())).thenReturn(Optional.of(new TaskModel(1L, "any_value", false)));

        sut.handle(input);

        verify(taskRepositoryMock, times(1)).findById(input);
    }

    @Test
    void should_throw_if_repository_returns_empty() {
        final Long input = 1L;
        when(taskRepositoryMock.findById(input)).thenReturn(Optional.empty());

        assertThrows(ModelNotFoundException.class, () -> sut.handle(input), "Task with id \"1\" was not found");
    }

    @Test
    void should_return_if_repository_returns() {
        final Long input = 1L;
        TaskModel expectedOutput = new TaskModel(1L, "any_value", true);
        when(taskRepositoryMock.findById(input)).thenReturn(Optional.of(new TaskModel(1L, "any_value", true)));

        TaskModel actual = sut.handle(input);

        assertEquals(expectedOutput, actual);
    }
}
