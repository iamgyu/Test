package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final Member_Sqlite memberSqlite = new Member_Sqlite();
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @PostMapping("/login")
    public String login(@RequestBody MemberDto memberDto){
        if(Integer.valueOf(memberSqlite.checkMember(memberDto.getId(), memberDto.getPassword())) != null){
            return jwtTokenProvider.generateToken(memberDto.getId());
        }
        return null;
    }

    @PostMapping("")
    public void input(@RequestBody MemberDto memberDto){
        memberSqlite.inputMember(memberDto.getId(), memberDto.getPassword());
    }

    @GetMapping("")
    public JSONObject read(){
        return memberSqlite.readMember();
    }
}
