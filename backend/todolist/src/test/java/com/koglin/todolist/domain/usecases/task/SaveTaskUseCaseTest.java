package com.koglin.todolist.domain.usecases.task;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.models.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class SaveTaskUseCaseTest {

    private TaskRepository taskRepositoryMock;
    private SaveTaskUseCase sut;

    @BeforeEach
    void beforeEach() {
        this.taskRepositoryMock = mock(TaskRepository.class);
        when(taskRepositoryMock.save(any(TaskModel.class))).thenReturn(new TaskModel(1L, "any_value", false));
        when(taskRepositoryMock.findById(any())).thenReturn(Optional.of(new TaskModel(1L, "any_value", false)));

        this.sut = new SaveTaskUseCase(this.taskRepositoryMock);
    }
    @Test
    void should_throw_if_empty_description_is_provided() {
        TaskModel task = new TaskModel(1L, "", false);

        assertThrows(RuntimeException.class, () -> sut.handle(task));
    }

    @Test
    void should_call_repository_save_with_correct_params() {
        TaskModel task = new TaskModel(null, "any_value", false);
        TaskModel expectedInput = new TaskModel(null, "any_value", false);

        sut.handle(task);

        verify(taskRepositoryMock).save(argThat(arg -> arg.equals(expectedInput)));
    }

    @Test
    void should_return_saved_task() {
        TaskModel input = new TaskModel(null, "any_value", false);
        when(taskRepositoryMock.save(any(TaskModel.class))).thenReturn(new TaskModel(15L, "any_value", false));

        TaskModel output = sut.handle(input);

        final TaskModel expectedOutput = new TaskModel(15L, "any_value", false);
        assertEquals(output, expectedOutput);
    }

}
