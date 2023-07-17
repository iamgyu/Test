package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    private Todo_Sqlite todo_sqlite = new Todo_Sqlite();    // drop table 을 위한 일회용

    @PostMapping("")
    public void input(@RequestHeader("Authorization") String encodeData, @RequestBody TodoDto todoDto){
        todoService.inputTodo(encodeData, todoDto);
    }

    @GetMapping("/all")
    public JSONObject inquireAll(){
        return todoService.getAllTodos();
    }

    @GetMapping("")
    public JSONObject inquire(@RequestHeader("Authorization") String encodeData){
        return todoService.getTodoById(encodeData);
    }

    @PatchMapping("/{pk}")
    public void update(@PathVariable int pk, @RequestHeader("Authorization") String encodeData, @RequestBody TodoDto todoDto){
        todoService.updateTodo(pk, encodeData, todoDto);
    }

    @DeleteMapping("/{pk}")
    public void remove(@PathVariable int pk, @RequestHeader("Authorization") String encodeData){
        todoService.removeTodo(pk, encodeData);
    }

    // drop table 을 위한 일회용 코드
    @GetMapping("/dropTable")
    public void alterTable() { todo_sqlite.dropTable(); }
}
