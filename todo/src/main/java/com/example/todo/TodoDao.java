package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoDao {

    private Todo_Sqlite todo_sqlite = new Todo_Sqlite();

    public JSONObject getAllTodos(){
        return todo_sqlite.selectDB();
    }

    public JSONObject getTodoById(String uuid) {
        return todo_sqlite.selectOneDB(uuid);
    }

    public void inputTodo(String uuid, TodoDto todoDto){
        todo_sqlite.insertDB(uuid, todoDto);
    }

    public void updateTodo(int pk, String uuid, TodoDto todoDto){
        todo_sqlite.updateDB(pk, uuid, todoDto);
    }

    public void deleteTodo(int pk, String uuid){
        todo_sqlite.deleteDB(pk, uuid);
    }
}
