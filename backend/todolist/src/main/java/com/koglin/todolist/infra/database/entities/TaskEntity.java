package com.koglin.todolist.infra.database.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean done;

    public TaskEntity(Long id, String description, Boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDone() {
        return done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(done, that.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
