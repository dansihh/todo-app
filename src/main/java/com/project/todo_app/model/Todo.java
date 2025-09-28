package com.project.todo_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Data; // For a more comprehensive solution


import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@Getter
@Setter
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Title Required")
    private String title;

    @NotNull
    @Size(max = 200, message = "Description cannot be NULL and more than 200")
    private String description;

    private boolean completed =  false;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime completedAt = LocalDateTime.now();

}
