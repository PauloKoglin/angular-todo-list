package com.koglin.todolist.domain.usecases.task;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.models.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class FindAllTasksUseCaseTest {

    private TaskRepository taskRepositoryMock;
    private FindAllTasksUseCase sut;

    @BeforeEach
    void beforeEach() {
        this.taskRepositoryMock = mock(TaskRepository.class);
        this.sut = new FindAllTasksUseCase(this.taskRepositoryMock);
    }

    @Test
    void should_call_repository_once() {
        sut.handle();

        verify(taskRepositoryMock, times(1)).findAll();
    }

    @Test
    void should_return_tasks_returned_from_repository() {
        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(new TaskModel(1L, "any_value", true));
        tasks.add(new TaskModel(2L, "any_value", false));
        when(taskRepositoryMock.findAll()).thenReturn(tasks);

        List<TaskModel> output = sut.handle();

        assertEquals(output, tasks);
    }
}
