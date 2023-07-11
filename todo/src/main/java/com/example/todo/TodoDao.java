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

    public JSONObject getTodoById(TodoDto todoDto) {
        return todo_sqlite.selectOneDB(todoDto);
    }

    public void inputTodo(TodoDto todoDto){
        todo_sqlite.insertDB(todoDto);
    }

    public void updateTodo(int pk, TodoDto todoDto){
        todo_sqlite.updateDB(pk, todoDto);
    }

    public void deleteTodo(int pk, TodoDto todoDto){
        todo_sqlite.deleteDB(pk, todoDto);
    }
}
