package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService {

    private Member_Sqlite member_sqlite = new Member_Sqlite();

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public void inputMember(String id, String password){
        member_sqlite.inputMember(id, password);
    }

    public JSONObject readMember(){
        return member_sqlite.readMember();
    }

    public int checkMember(String id, String password){
        return member_sqlite.checkMember(id, password);
    }

    public int checkMemberId(String id){
        return member_sqlite.checkMemberId(id);
    }

    public JSONObject login(String id){
        Map<String, String> map = new HashMap<>();

        // Access Token 생성
        String accessToken = jwtTokenProvider.generateAccessToken(id);

        // Refresh Token 생성
        String refreshToken = jwtTokenProvider.generateRefreshToken(id);

        map.put("Access Token", accessToken);
        map.put("Refresh Token", refreshToken);

        return new JSONObject(map);
    }

    public JSONObject refresh(String refreshToken){
        // Refresh Token 확인
        if(!jwtTokenProvider.getTokenTypeFromJWT(refreshToken).equals("refreshToken")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Map<String, String> map = new HashMap<>();

        // Access Token 새로 생성
        String id = jwtTokenProvider.getUserIdFromJWT(refreshToken);
        String remakeAccessToken = jwtTokenProvider.generateAccessToken(id);
        map.put("accessToken", remakeAccessToken);

        // Refresh Token expire 7일 이하면 새로 생성
        if(jwtTokenProvider.getExpireRemainFromJWT(refreshToken) <= 604800000L) {
            String remakeRefreshToken = jwtTokenProvider.generateRefreshToken(id);
            map.put("refreshToken", remakeRefreshToken);
        }

        return new JSONObject(map);
    }
}
