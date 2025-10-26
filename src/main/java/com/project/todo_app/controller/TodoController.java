package com.project.todo_app.controller;

import com.project.todo_app.model.Todo;
import com.project.todo_app.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> createTask(@Valid @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.createTask(todo));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTasks() {
        return ResponseEntity.ok(todoService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTask(@PathVariable Long id, @Valid @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.updateTask(id, todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        todoService.deleteTask(id);
        return ResponseEntity.ok("Task has been deleted successfully");
    }
}
