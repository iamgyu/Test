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

    public JSONObject getTodoById(String id) {
        return todo_sqlite.selectOneDB(id);
    }

    public void inputTodo(String id, TodoDto todoDto){
        todo_sqlite.insertDB(id, todoDto);
    }

    public void updateTodo(int pk, String id, TodoDto todoDto){
        todo_sqlite.updateDB(pk, id, todoDto);
    }

    public void deleteTodo(int pk, String id){
        todo_sqlite.deleteDB(pk, id);
    }
}
