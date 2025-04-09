package com.example.awstodoapplication.Controller;

import com.example.awstodoapplication.Model.Todo;
import com.example.awstodoapplication.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {



    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        model.addAttribute("todo", new Todo());
        return "index";
    }

    @PostMapping("/todo")
    public String createTodo(@RequestParam String title, @RequestParam String description) {
        todoService.createTodo(title, description);
        return "redirect:/";
    }

    @GetMapping("/todo/{id}")
    public String editTodoForm(@PathVariable String id, Model model) {
        Todo todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        model.addAttribute("todos", todoService.getAllTodos());
        return "index";
    }

    @PostMapping("/todo/{id}")
    public String updateTodo(@PathVariable String id,
                             @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam(required = false, defaultValue = "false") boolean completed) {
        todoService.updateTodo(id, title, description, completed);
        return "redirect:/";
    }

    @GetMapping("/todo/{id}/delete")
    public String deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }

    @GetMapping("/todo/{id}/toggle")
    public String toggleCompleted(@PathVariable String id) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            todoService.updateTodo(id, todo.getTitle(), todo.getDescription(), !todo.isCompleted());
        }
        return "redirect:/";
    }
}
