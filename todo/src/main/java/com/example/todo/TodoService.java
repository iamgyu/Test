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

    public JSONObject getTodoById(String uuid){
        return todoDao.getTodoById(uuid);
    }

    public void inputTodo(String uuid, TodoDto todoDto){
        todoDao.inputTodo(uuid, todoDto);
    }

    public void updateTodo(int pk, String uuid, TodoDto todoDto){
        todoDao.updateTodo(pk, uuid, todoDto);
    }

    public void removeTodo(int pk, String uuid){
        todoDao.deleteTodo(pk, uuid);
    }
}
