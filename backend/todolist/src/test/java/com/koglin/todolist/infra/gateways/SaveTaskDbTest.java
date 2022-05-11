package com.koglin.todolist.infra.gateways;

import com.koglin.todolist.domain.models.TaskModel;
import com.koglin.todolist.domain.useCases.SaveTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SaveTaskDbTest {

    @Test
    void Should_throw_if_empty_description_is_provided() {
        SaveTask sut = new SaveTaskDb();
        TaskModel task = new TaskModel(1, "", false);

        Assertions.assertThrows(RuntimeException.class, () -> sut.perform(task));
    }

}
