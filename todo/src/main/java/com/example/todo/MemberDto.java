package com.example.todo;

import java.util.HashMap;
import java.util.Map;

public class MemberDto {
    private String id;
    private String password;
    public static Map<String, String> mapping = new HashMap<>();
    public MemberDto() {}
    public MemberDto(String id, String password){
        this.id = id;
        this.password = password;
    }

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
}
