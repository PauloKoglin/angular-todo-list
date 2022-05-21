package com.koglin.todolist.domain.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class ProjectServiceTest {

    record ProjectModel (
            Long id,
            String name,
            Date createdAt
    ) { }

    class ProjectService {

        public ProjectModel create(ProjectModel project) {
            if (project.name.isEmpty()) {
                throw new RuntimeException("Project name can not be empty.");
            }
            return null;
        }
    }

    private ProjectService sut;

    @BeforeEach
    void setup() {
        sut = new ProjectService();
    }

    @Test
    void create_should_throw_exception_if_given_projects_name_is_empty() {
        final ProjectModel input = new ProjectModel(null, "", new Date());

        Exception exception = assertThrows(RuntimeException.class, () -> sut.create(input));
        assertEquals("Project name can not be empty.", exception.getMessage());
    }
}
