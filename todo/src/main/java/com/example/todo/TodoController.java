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

    private Todo_Sqlite todo_sqlite = new Todo_Sqlite();    // drop table 을 위한 일회용
    private Member_Sqlite member_sqlite = new Member_Sqlite();  // login 성공 여부를 위한 객체 선언
    Map<String, String> mapping = MemberDto.mapping;
    @PostMapping("/login")
    public void login(@RequestBody TodoDto todoDto){
        int result = member_sqlite.checkMember(todoDto.getId(), todoDto.getPassword());
        if(Integer.valueOf(result) != null){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            mapping.put(todoDto.getId(), uuid);
        }
    }

    @GetMapping("/login")
    public void printMap(){
        System.out.println(mapping);
    }

    @PostMapping("")
    public void input(@RequestHeader("Authorization") String uuid, @RequestBody TodoDto todoDto){
        todoService.inputTodo(uuid, todoDto);
    }

    @GetMapping("/all")
    public JSONObject inquireAll(){
        return todoService.getAllTodos();
    }

    @GetMapping("")
    public JSONObject inquire(@RequestHeader("Authorization") String uuid){
        return todoService.getTodoById(uuid);
    }

    @PatchMapping("/{pk}")
    public void update(@PathVariable int pk, @RequestHeader("Authorization") String uuid, @RequestBody TodoDto todoDto){
        todoService.updateTodo(pk, uuid, todoDto);
    }

    @DeleteMapping("/{pk}")
    public void remove(@PathVariable int pk, @RequestHeader("Authorization") String uuid){
        todoService.removeTodo(pk, uuid);
    }

    // drop table 을 위한 일회용 코드
    @GetMapping("/dropTable")
    public void alterTable() { todo_sqlite.dropTable(); }
}
