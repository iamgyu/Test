package com.example.todo;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${jwt.secretKey}")
    private String jwt_secret;

    // jwt 토큰 생성
    public String generateToken(String id){
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 3600000);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .claim("id", id)
                .signWith(SignatureAlgorithm.HS256, jwt_secret)
                .compact();
    }

    // jwt 토큰에서 아이디 추출
    public String getUserIdFromJWT(String token){
        validateToken(token);
        Claims claims = Jwts.parser()
                .setSigningKey(jwt_secret)
                .parseClaimsJws(token)
                .getBody();

        return (String) claims.get("id");
    }

    // Jwt 토큰 유효성 검사
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty.", e);
        }
        return false;
    }
}
