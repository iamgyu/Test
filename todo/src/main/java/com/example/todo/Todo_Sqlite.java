package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.lang.reflect.Member;
import java.sql.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Todo_Sqlite {
    public static final String DB_URL = "jdbc:sqlite:todo.db";
    private Member_Sqlite member_sqlite = new Member_Sqlite();

    public void insertDB(String uuid, TodoDto todoDto){
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS todos (pk INTEGER PRIMARY KEY, " +
                    "title TEXT NOT NULL, detail TEXT NOT NULL, done BOOLEAN NOT NULL," +
                    "account_id INTEGER, FOREIGN KEY (account_id) REFERENCES members(pk))";
            stmt.executeUpdate(sql);
            stmt.close();

            /*
            String[] resultDecode = Base64Decoder(encodeData);
            String id = resultDecode[0];
            String pwd = resultDecode[1];

            int check_member = member_sqlite.checkMember(id, pwd);   // return member pk
            */

            int check_member = member_sqlite.checkMembeUUID(uuid);
            if(check_member != 0) {
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO todos(title, detail, done, account_id) VALUES(?, ?, ?, ?)");
                pstmt.setString(1, todoDto.getTitle());
                pstmt.setString(2, todoDto.getDetail());
                pstmt.setBoolean(3, todoDto.isDone());
                pstmt.setInt(4, check_member);
                pstmt.executeUpdate();
                pstmt.close();
            }
            conn.close();
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
                 int account_id = rs.getInt("account_id");
                 todo.put("account_id", account_id);
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

    public JSONObject selectOneDB(String uuid){
        Map<String, Object> map = new HashMap<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            /*
            String[] resultDecode = Base64Decoder(encodeData);
            String id = resultDecode[0];
            String pwd = resultDecode[1];

            int check_member = member_sqlite.checkMember(id, pwd);   // return member pk
            */

            int check_member = member_sqlite.checkMembeUUID(uuid);  // return member pk
            ResultSet rs = stmt.executeQuery("SELECT * FROM todos WHERE account_id = " + check_member + ";");

            while (rs.next()) {
                Map<String, Object> todo = new HashMap<>();
                int pk = rs.getInt("pk");
                todo.put("pk", pk);
                todo.put("account_id", check_member);

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
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }

    public void updateDB(int pk, String uuid, TodoDto todoDto){
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            /*
            String[] resultDecode = Base64Decoder(encodeData);
            String id = resultDecode[0];
            String pwd = resultDecode[1];

            int check_member = member_sqlite.checkMember(id, pwd);   // return member pk
             */
            int check_member = member_sqlite.checkMembeUUID(uuid);
            String checkSql = "SELECT account_id FROM todos WHERE pk = " + pk + ";";
            ResultSet rs = stmt.executeQuery(checkSql);
            int check_account_id = rs.getInt(1);    // 해당 할 일에 대한 account_id 반환

            rs.close();
            stmt.close();

            // 할 일이 존재하지 않으면 404에러 발생
            if(check_account_id == 0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            // 계정에 해당 할 일이 없는 경우 403에러 발생
            if(check_member != check_account_id){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }

            Statement stmt2 = conn.createStatement();
            String sql = "UPDATE todos SET ";
            if (todoDto.getTitle() != null){
                sql +=  "title = '" + todoDto.getTitle() + "',";
            } if (todoDto.getDetail() != null){
                sql += "detail = '" + todoDto.getDetail() + "',";
            } if (String.valueOf(todoDto.isDone()) != null){
                sql += "done = " + todoDto.isDone() + ",";
            } sql = sql.substring(0, sql.length() - 1);
            sql += " WHERE pk = " + pk + ";";

            stmt2.executeUpdate(sql);
            stmt2.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteDB(int pk, String uuid){
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            /*
            String[] resultDecode = Base64Decoder(encodeData);
            String id = resultDecode[0];
            String pwd = resultDecode[1];

            int check_member = member_sqlite.checkMember(id, pwd);   // return member pk
             */

            int check_member = member_sqlite.checkMembeUUID(uuid);
            String checkSql = "SELECT account_id FROM todos WHERE pk = " + pk + ";";
            ResultSet rs = stmt.executeQuery(checkSql);
            int check_account_id = rs.getInt(1);    // 해당 할 일에 대한 account_id 반환

            rs.close();
            stmt.close();

            // 할 일이 존재하지 않으면 404에러 발생
            if(check_account_id == 0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            // 계정에 해당 할 일이 없는 경우 403에러 발생
            if(check_member != check_account_id){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }

            Statement stmt2 = conn.createStatement();
            String sql = "DELETE FROM todos WHERE pk = " + pk + ";";
            stmt2.executeUpdate(sql);

            stmt2.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] Base64Decoder(String encodedData){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedData);
        String decodedData = new String(decodedBytes);
        String[] result = decodedData.split(":");

        return result;
    }

    public void dropTable(){
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String sql = "Drop table todos";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
