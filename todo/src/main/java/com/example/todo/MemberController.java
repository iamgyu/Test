package com.example.todo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("")
    public void input(@RequestBody MemberDto memberDto){
        memberService.inputMember(memberDto.getId(), memberDto.getPassword());
    }

    @GetMapping("")
    public JSONObject read(){
        return memberService.readMember();
    }

    @PostMapping("/login")
    public JSONObject login(@RequestBody MemberDto memberDto){
        if(Integer.valueOf(memberService.checkMember(memberDto.getId(), memberDto.getPassword())) != null){
            return memberService.login(memberDto.getId());
        }
        return null;
    }

    @PostMapping("/refresh")
    public JSONObject refresh(@RequestHeader("Authorization") String refreshToken){
        return memberService.refresh(refreshToken);
    }
}
