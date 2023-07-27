package com.example.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenDto {
    private String grantType;   // JWT에 대한 인증 타입
    private String accessToken;
    //private String refreshToken;
}
