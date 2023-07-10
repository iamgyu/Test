package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("")
    public void input(@RequestBody TodoDto todoDto){
        todoService.inputTodo(todoDto);
    }

    @GetMapping("")
    public JSONObject inquireAll(){
        return todoService.getAllTodos();
    }

    @GetMapping("/{pk}")
    public JSONObject inquire(@PathVariable int pk){
        return todoService.getTodoById(pk);
    }

    @PatchMapping("/{pk}")
    public void update(@PathVariable int pk, @RequestBody TodoDto todoDto){
        todoService.updateTodo(pk, todoDto);
    }

    @DeleteMapping("/{pk}")
    public void remove(@PathVariable int pk){
        todoService.removeTodo(pk);
    }
}
