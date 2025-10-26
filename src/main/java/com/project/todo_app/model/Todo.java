package com.project.todo_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "todo")
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title Required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Description Required")
    @Size(max = 200, message = "Description cannot be NULL and more than 200")
    @Column(nullable = false, length = 200)
    private String description;

    private boolean completed = false;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime completedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (completed) {
            completedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (completed && completedAt == null) {
            completedAt = LocalDateTime.now();
        }
        if (!completed) {
            completedAt = null;
        }
    }

}
