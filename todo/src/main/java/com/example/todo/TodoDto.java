package com.example.todo;

public class TodoDto {
    private String title;
    private String detail;
    private boolean done;
    private String id;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public TodoDto() {}
    public TodoDto(String title, String detail, String id, String password){
        this.title = title;
        this.detail = detail;
        this.done = false;
        this.id = id;
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void update(TodoDto todoDto){
        if(todoDto.getTitle() != null) this.title = todoDto.title;
        if(todoDto.getDetail() != null) this.detail = todoDto.detail;
        if((Boolean) todoDto.isDone() != null) this.done = todoDto.done;
    }
}
