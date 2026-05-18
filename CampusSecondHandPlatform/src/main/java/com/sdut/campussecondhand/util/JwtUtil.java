package com.sdut.campussecondhand.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT令牌工具类
 * 用于生成和验证登录令牌
 * @author 孟冠宇
 * @date 2026-05-18
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成JWT令牌
     * @param userId 用户ID
     * @return 令牌字符串
     */
    public String generateToken(String userId) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expiration);
        
        return Jwts.builder()
                .setSubject(userId) // 主题：用户ID
                .setIssuedAt(now) // 签发时间
                .setExpiration(expireDate) // 过期时间
                .signWith(SignatureAlgorithm.HS256, secret) // 签名算法
                .compact();
    }

    /**
     * 从令牌中获取用户ID
     * @param token JWT令牌
     * @return 用户ID
     */
    public String getUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证令牌是否有效
     * @param token JWT令牌
     * @return true有效，false无效
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}