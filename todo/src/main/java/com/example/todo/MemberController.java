package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final Member_Sqlite memberSqlite = new Member_Sqlite();

    @PostMapping("")
    public void input(@RequestBody MemberDto memberDto){
        memberSqlite.inputMember(memberDto.getId(), memberDto.getPassword());
    }

    @GetMapping("")
    public JSONObject read(){
        return memberSqlite.readMember();
    }
}
