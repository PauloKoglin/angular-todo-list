package com.koglin.todolist.domain.usecases.task;

import com.koglin.todolist.domain.contracts.gateways.EventDispatcher;
import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.events.EventIdentification;
import com.koglin.todolist.domain.events.TaskDeletedEventContent;
import com.koglin.todolist.domain.exceptions.ModelNotFoundException;
import com.koglin.todolist.domain.models.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DeleteTaskByIdUseCaseTest {

    private TaskRepository taskRepositoryMock;
    private FindTaskByIdUseCase findTaskByIdUseCaseMock;

    private EventDispatcher<TaskDeletedEventContent> eventDispatcher;
    private DeleteTaskByIdUseCase sut;

    @BeforeEach
    void beforeEach() {
        this.taskRepositoryMock = mock(TaskRepository.class);
        this.findTaskByIdUseCaseMock = mock(FindTaskByIdUseCase.class);
        this.eventDispatcher = mock(EventDispatcher.class);

        when(taskRepositoryMock.save(any(TaskModel.class))).thenReturn(new TaskModel(1L, "any_value", false));
        when(findTaskByIdUseCaseMock.handle(any())).thenReturn(new TaskModel(1L, "any_value", false));

        this.sut = new DeleteTaskByIdUseCase(
            this.taskRepositoryMock,
            this.findTaskByIdUseCaseMock,
            this.eventDispatcher
        );
    }

    @Test
    void should_call_FindTaskById_with_correct_param() {
        sut.handle(1L);

        verify(findTaskByIdUseCaseMock, times(1)).handle(1L);
    }

    @Test
    void should_throw_if_FindTaskById_returns_empty() {
        final Long input = 1L;
        when(findTaskByIdUseCaseMock.handle(input)).thenThrow(new ModelNotFoundException("any_error"));

        assertThrows(ModelNotFoundException.class, () -> sut.handle(input), "any_error");
    }

    @Test
    void should_call_repository_delete_if_task_was_found() {
        final Long input = 1L;
        final TaskModel findByIdResult = new TaskModel(input, "any_value", false);
        when(findTaskByIdUseCaseMock.handle(input)).thenReturn(findByIdResult);

        sut.handle(input);

        verify(taskRepositoryMock, times(1)).delete(input);
    }

    @Test
    void should_return_deleted_task() {
        final Long input = 1L;
        final TaskModel findByIdResult = new TaskModel(input, "any_value", false);
        when(findTaskByIdUseCaseMock.handle(input)).thenReturn(findByIdResult);

        TaskModel output = sut.handle(input);

        assertEquals(findByIdResult, output);
    }

    @Test
    void should_dispatch_event_if_task_has_been_deleted() {
        when(findTaskByIdUseCaseMock.handle(any())).thenReturn(new TaskModel(999L, "any_description", false));
        final Long input = 1L;

        TaskModel output = sut.handle(input);

        TaskDeletedEventContent expectedContent = new TaskDeletedEventContent(999L, "any_description");
        verify(eventDispatcher, times(1)).dispatch(EventIdentification.TASK_DELETED, expectedContent);
    }
}
