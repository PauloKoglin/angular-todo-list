package com.koglin.todolist.presentation.controllers;

import com.koglin.todolist.infra.database.entities.TaskEntity;
import com.koglin.todolist.infra.database.repositories.TaskRepositoryJpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TaskControllerITTest {

    @Autowired
    TaskRepositoryJpa repositoryJpa;

    private void insertTasksToDatabase() {
        repositoryJpa.saveAll(List.of(
                new TaskEntity(1L, "task 1", false),
                new TaskEntity(2L, "task 2", true)
        ));
    }

    @Test
    public void get_should_return_status_code_200_with_correct_response(@Autowired WebTestClient client) {
        this.insertTasksToDatabase();
        final String expectedJson = """                
                [
                    {
                        "id": 1,
                        "description": "task 1",
                        "done": false
                    },
                    {
                        "id": 2,
                        "description": "task 2",
                        "done": true
                    }
                ]
                """;

        client.get().uri("/tasks")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(expectedJson);
    }

    @Test
    public void get_should_return_status_code_200_with_empty_task_list(@Autowired WebTestClient client) {
        final String expectedJson = "[]";

        client.get().uri("/tasks")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(expectedJson);
    }

    @Test
    public void getById_should_return_status_code_200_with_correct_response_body(@Autowired WebTestClient client) {
        this.insertTasksToDatabase();
        final String expectedJson = """
                    {
                        "id": 1,
                        "description": "task 1",
                        "done": false
                    }
                """;

        client.get().uri("/tasks/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(expectedJson);
    }
}
