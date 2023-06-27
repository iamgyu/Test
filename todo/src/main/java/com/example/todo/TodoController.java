package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("")
    public TodoDto input(@RequestBody TodoDto todoDto){
        return todoService.inputTodo(todoDto);
    }

    @GetMapping("")
    public List<TodoDto> inquireAll(){
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public TodoDto inquire(@PathVariable int id){
        return todoService.getTodoById(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody TodoDto todoDto){
        todoService.updateTodo(id, todoDto);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id){
        todoService.removeTodo(id);
    }
}
