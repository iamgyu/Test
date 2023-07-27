package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final Member_Sqlite memberSqlite = new Member_Sqlite();

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody MemberDto memberDto){
        String id = memberDto.getId();
        String password = memberDto.getPassword();
        TokenDto tokenDto = memberService.login(id, password);
        return tokenDto;
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
