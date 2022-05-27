package com.koglin.todolist.presentation.controllers;

import com.koglin.todolist.infra.database.entities.TaskEntity;
import com.koglin.todolist.infra.database.repositories.TaskRepositoryJpa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskControllerITTest {

    @Autowired
    TaskRepositoryJpa repositoryJpa;

    @BeforeAll
    public void beforeAll() {
        this.setupDatabase();
    }

    private void setupDatabase() {
        repositoryJpa.saveAll(List.of(
                new TaskEntity(null, "task 1", false),
                new TaskEntity(null, "task 2", true)
        ));
    }

    @Test
    public void get_should_return_status_code_200_with_correct_response(@Autowired WebTestClient client) {
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
}
