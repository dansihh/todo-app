package com.project.todo_app.service;

import com.project.todo_app.model.Todo;
import com.project.todo_app.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createTask(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTasks() {
        return todoRepository.findAll();
    }

    public Todo getTaskById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task is not available: " + id));
    }

    public Todo updateTask(Long id, Todo updatedTodo) {

        Todo existing = getTaskById(id);
        existing.setTitle(updatedTodo.getTitle());
        existing.setDescription(updatedTodo.getDescription());
        existing.setCompleted(updatedTodo.isCompleted());
        return todoRepository.save(existing);

    }

    public void deleteTask(Long id) {
        Todo existing = getTaskById(id);
        todoRepository.delete(existing);
    }

}
