package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("")
    public void input(@RequestHeader("Authorization") String token, @RequestBody TodoDto todoDto){
        String id = jwtTokenProvider.getUserIdFromJWT(token);
        todoService.inputTodo(id, todoDto);
    }

    @GetMapping("/all")
    public JSONObject inquireAll(){
        return todoService.getAllTodos();
    }

    @GetMapping("")
    public JSONObject inquire(@RequestHeader("Authorization") String token){
        String id = jwtTokenProvider.getUserIdFromJWT(token);
        return todoService.getTodoById(id);
    }

    @PatchMapping("/{pk}")
    public void update(@PathVariable int pk, @RequestHeader("Authorization") String token, @RequestBody TodoDto todoDto){
        String id = jwtTokenProvider.getUserIdFromJWT(token);
        todoService.updateTodo(pk, id, todoDto);
    }

    @DeleteMapping("/{pk}")
    public void remove(@PathVariable int pk, @RequestHeader("Authorization") String token){
        String id = jwtTokenProvider.getUserIdFromJWT(token);
        todoService.removeTodo(pk, id);
    }
}
