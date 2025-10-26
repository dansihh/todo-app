// ...existing code...
package com.project.todo_app.service;

import com.project.todo_app.model.Todo;
import com.project.todo_app.repository.TodoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createTask(Todo todo) {
        if (todo == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        // if client creates a task already marked completed, set completedAt
        if (todo.isCompleted() && todo.getCompletedAt() == null) {
            todo.setCompletedAt(LocalDateTime.now());
        }
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTasks() {
        return todoRepository.findAll();
    }

    public Todo getTaskById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task is not available: " + id));
    }

    @Transactional
    public Todo updateTask(Long id, Todo updatedTodo) {

        Todo existing = getTaskById(id);
        existing.setTitle(updatedTodo.getTitle());
        existing.setDescription(updatedTodo.getDescription());

        boolean wasCompleted = existing.isCompleted();
        boolean nowCompleted = updatedTodo.isCompleted();

        existing.setCompleted(nowCompleted);

        if (!wasCompleted && nowCompleted) {
            // transitioned from not-completed -> completed
            existing.setCompletedAt(LocalDateTime.now());
        } else if (!nowCompleted) {
            // ensure completedAt is cleared when un-marked
            existing.setCompletedAt(null);
        }

        return todoRepository.save(existing);

    }

    @Transactional
    public void deleteTask(Long id) {
        Todo existing = getTaskById(id);
        todoRepository.delete(existing);
    }

}