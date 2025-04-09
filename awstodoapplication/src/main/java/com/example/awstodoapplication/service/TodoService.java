package com.example.awstodoapplication.service;
import com.example.awstodoapplication.Model.Todo;
import com.example.awstodoapplication.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(String id) {
        return todoRepository.findById(id);
    }

    public void  createTodo(String title, String description) {
        Todo todo = new Todo(title, description);
         todoRepository.save(todo);
    }

    public void  updateTodo(String id, String title, String description, boolean completed) {
        Todo todo = todoRepository.findById(id);
        if (todo != null) {
            todo.update(title, description, completed);
            todoRepository.save(todo);
        }
    }

    public void deleteTodo(String id) {
        Todo todo = todoRepository.findById(id);
        if (todo != null) {
            todoRepository.delete(todo);
        }
    }

}
