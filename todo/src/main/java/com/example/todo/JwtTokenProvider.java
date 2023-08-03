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
    private String generateToken(String id, String tokenType, Date expireDate){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .claim("id", id)
                .claim("tokenType", tokenType)
                .signWith(SignatureAlgorithm.HS256, jwt_secret)
                .compact();
    }

    public String generateAccessToken(String id){
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 3600000);

        return generateToken(id, "accessToken", expireDate);
    }

    public String generateRefreshToken(String id){
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 2592000000L);

        return generateToken(id, "refreshToken", expireDate);
    }

    public Claims parseJwtToken(String token){
        validateToken(token);

        return Jwts.parser()
                .setSigningKey(jwt_secret)
                .parseClaimsJws(token)
                .getBody();
    }

    // jwt 토큰에서 아이디 추출
    public String getUserIdFromJWT(String token){
        Claims claims = parseJwtToken(token);
        return (String) claims.get("id");
    }

    // jwt 토큰에서 타입 추출
    public String getTokenTypeFromJWT(String token){
        Claims claims = parseJwtToken(token);
        return (String) claims.get("tokenType");
    }

    // jwt 토큰에서 expire 추출
    private Date getExpireFromJWT(String token){
        Claims claims = parseJwtToken(token);
        return claims.getExpiration();
    }

    // jwt 토큰에서 expire 남은 시간 계산
    public long getExpireRemainFromJWT(String token){
        Date now = new Date();
        return getExpireFromJWT(token).getTime() - now.getTime();
    }
    
    // jwt 토큰 유효성 검사
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
