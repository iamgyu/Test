package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoDao todoDao;

    public JSONObject getAllTodos(){
        return todoDao.getAllTodos();
    }

    public JSONObject getTodoById(String encodeData){
        return todoDao.getTodoById(encodeData);
    }

    public void inputTodo(String encodeData, TodoDto todoDto){
        todoDao.inputTodo(encodeData, todoDto);
    }

    public void updateTodo(int pk, String encodeData, TodoDto todoDto){
        todoDao.updateTodo(pk, encodeData, todoDto);
    }

    public void removeTodo(int pk, String encodeData){
        todoDao.deleteTodo(pk, encodeData);
    }
}
