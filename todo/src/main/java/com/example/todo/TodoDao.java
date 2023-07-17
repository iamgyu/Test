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

    public JSONObject getTodoById(String encodeData) {
        return todo_sqlite.selectOneDB(encodeData);
    }

    public void inputTodo(String encodeData, TodoDto todoDto){
        todo_sqlite.insertDB(encodeData, todoDto);
    }

    public void updateTodo(int pk, String encodeData, TodoDto todoDto){
        todo_sqlite.updateDB(pk, encodeData, todoDto);
    }

    public void deleteTodo(int pk, String encodeData){
        todo_sqlite.deleteDB(pk, encodeData);
    }
}
