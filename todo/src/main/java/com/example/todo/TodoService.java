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

    public JSONObject getTodoById(String id){
        return todoDao.getTodoById(id);
    }

    public void inputTodo(String id, TodoDto todoDto){
        todoDao.inputTodo(id, todoDto);
    }

    public void updateTodo(int pk, String id, TodoDto todoDto){
        todoDao.updateTodo(pk, id, todoDto);
    }

    public void removeTodo(int pk, String id){
        todoDao.deleteTodo(pk, id);
    }
}
