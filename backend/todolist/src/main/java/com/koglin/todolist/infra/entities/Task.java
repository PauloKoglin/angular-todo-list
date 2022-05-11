package com.koglin.todolist.infra.entities;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean done;

    public Task(Long id, String description, Boolean done) {
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
}
