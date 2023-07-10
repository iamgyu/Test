package com.example.todo;

public class TodoDto {
    private String title;
    private String detail;
    private boolean done;

    public TodoDto() {}
    public TodoDto(String title, String detail){
        this.title = title;
        this.detail = detail;
        this.done = false;
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
