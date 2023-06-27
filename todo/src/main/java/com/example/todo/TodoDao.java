package com.example.todo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoDao {
    public static List<TodoDto> todos;

    static{
        todos = new ArrayList<>();
        todos.add(new TodoDto(1, "title1", "detail1"));
        todos.add(new TodoDto(2, "title2", "detail2"));
        todos.add(new TodoDto(3, "title3", "detail3"));
        todos.add(new TodoDto(4, "title4", "detail4"));
        todos.add(new TodoDto(5, "title5", "detail5"));
    }

    public List<TodoDto> getAllTodos(){
        return todos;
    }

    public TodoDto getTodoById(int id) {
        return todos
                .stream()
                .filter(todo -> todo.getId() == id)
                .findAny()
                .orElse(new TodoDto(-1, "", ""));
    }

    public TodoDto inputTodo(TodoDto todoDto){
        todos.add(todoDto);

        return todoDto;
    }

    public void updateTodo(int id, TodoDto todoDto){
        todos
                .stream()
                .filter(todo -> todo.getId() == id)
                .findAny()
                .orElse(new TodoDto(-1, "", ""))
                .update(todoDto);
    }

    public void deleteTodo(int id){
        todos.removeIf(todo -> todo.getId() == id);
    }
}
