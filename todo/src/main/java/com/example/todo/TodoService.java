package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoDao todoDao;

    public List<TodoDto> getAllTodos(){
        return todoDao.getAllTodos();
    }

    public TodoDto getTodoById(int id){
        return todoDao.getTodoById(id);
    }

    public TodoDto inputTodo(TodoDto todoDto){
        return todoDao.inputTodo(todoDto);
    }

    public void updateTodo(int id, TodoDto todoDto){
        todoDao.updateTodo(id, todoDto);
    }

    public void removeTodo(int id){
        todoDao.deleteTodo(id);
    }
}
