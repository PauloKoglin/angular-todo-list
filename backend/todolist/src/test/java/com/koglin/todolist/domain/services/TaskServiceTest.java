package com.koglin.todolist.domain.services;

import com.koglin.todolist.domain.contracts.repositories.TaskRepository;
import com.koglin.todolist.domain.models.TaskModel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private TaskRepository taskRepositoryMock;
    private TaskService sut;

    @BeforeEach
    void beforeEach() {
        this.taskRepositoryMock = mock(TaskRepository.class);
        when(taskRepositoryMock.save(any(TaskModel.class))).thenReturn(new TaskModel(1L, "any_value", false));

        this.sut = new TaskService(this.taskRepositoryMock);
    }
    @Test
    void Should_throw_if_empty_description_is_provided() {
        TaskModel task = new TaskModel(1L, "", false);

        assertThrows(RuntimeException.class, () -> sut.save(task));
    }

    @Test
    void Should_call_repository_save_with_correct_params() {
        TaskModel task = new TaskModel(null, "any_value", false);
        TaskModel expectedInput = new TaskModel(null, "any_value", false);

        sut.save(task);

        verify(taskRepositoryMock).save(argThat(arg -> arg.equals(expectedInput)));
    }

    @Test
    void Should_return_saved_task() {
        TaskModel input = new TaskModel(null, "any_value", false);
        when(taskRepositoryMock.save(any(TaskModel.class))).thenReturn(new TaskModel(15L, "any_value", false));

        TaskModel output = sut.save(input);

        final TaskModel expectedOutput = new TaskModel(15L, "any_value", false);
        assertEquals(output, expectedOutput);
    }

    @Test
    void findAll_should_call_repository_once() {
        sut.findAll();

        verify(taskRepositoryMock, times(1)).findAll();
    }

    @Test
    void Should_return_tasks_returned_from_repository() {
        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(new TaskModel(1L, "any_value", true));
        tasks.add(new TaskModel(2L, "any_value", false));
        when(taskRepositoryMock.findAll()).thenReturn(tasks);

        List<TaskModel> output = sut.findAll();

        assertEquals(output, tasks);
    }
}