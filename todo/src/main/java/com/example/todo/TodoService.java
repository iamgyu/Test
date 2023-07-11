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

    public JSONObject getTodoById(TodoDto todoDto){
        return todoDao.getTodoById(todoDto);
    }

    public void inputTodo(TodoDto todoDto){
        todoDao.inputTodo(todoDto);
    }

    public void updateTodo(int pk, TodoDto todoDto){
        todoDao.updateTodo(pk, todoDto);
    }

    public void removeTodo(int pk, TodoDto todoDto){
        todoDao.deleteTodo(pk, todoDto);
    }
}
