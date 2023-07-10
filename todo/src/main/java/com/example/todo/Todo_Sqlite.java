package com.example.todo;

import org.json.simple.JSONObject;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Todo_Sqlite {
    public static final String DB_URL = "jdbc:sqlite:todo.db";

    public void insertDB(TodoDto todoDto){
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS todos (pk INTEGER PRIMARY KEY, " +
                    "title TEXT NOT NULL, detail TEXT NOT NULL, done BOOLEAN NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO todos(title, detail, done) VALUES(?, ?, ?)");
            pstmt.setString(1, todoDto.getTitle());
            pstmt.setString(2, todoDto.getDetail());
            pstmt.setBoolean(3, todoDto.isDone());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JSONObject selectDB(){
        Map<String, Object> map = new HashMap<>();
         try {
             Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM todos");

             while(rs.next()){
                 Map<String, Object> todo = new HashMap<>();
                 int pk = rs.getInt("pk");
                 todo.put("pk", pk);
                 String title = rs.getString("title");
                 todo.put("title", title);
                 String detail = rs.getString("detail");
                 todo.put("detail", detail);
                 Boolean done = rs.getBoolean("done");
                 todo.put("done", done);
                 map.put("todo" + pk, todo);
             }
             rs.close();
             stmt.close();
             conn.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }

         JSONObject jsonObject = new JSONObject(map);
         return jsonObject;
    }

    public JSONObject selectOneDB(int pk){
        Map<String, Object> todo = new HashMap<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM todos WHERE pk = " + pk + ";");

            todo.put("pk", pk);
            String title = rs.getString("title");
            todo.put("title", title);
            String detail = rs.getString("detail");
            todo.put("detail", detail);
            Boolean done = rs.getBoolean("done");
            todo.put("done", done);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = new JSONObject(todo);
        return jsonObject;
    }
    public void updateDB(int pk, TodoDto todoDto){
        try {
            Connection conn = null;
            conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            String sql = "UPDATE todos SET ";
            if (todoDto.getTitle() != null){
                sql +=  "title = '" + todoDto.getTitle() + "',";
            } if (todoDto.getDetail() != null){
                sql += "detail = '" + todoDto.getDetail() + "',";
            } if (String.valueOf(todoDto.isDone()) != null){
                sql += "done = " + todoDto.isDone() + ",";
            } sql = sql.substring(0, sql.length() - 1);
            sql += " WHERE pk = " + pk + ";";

            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDB(int pk){
        try {
            Connection conn = null;
            conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM todos WHERE pk = " + pk + ";";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
