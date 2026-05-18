package com.project.easywork.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
  private final long accessTokenValidity;
  private final long refreshTokenValidity;
  
  private final SecretKey secretKey;
  
  public JwtTokenProvider(
      @Value("${jwt.secret}") String secret,
      @Value("${jwt.access-token-validity}") long accessTokenValidity,
      @Value("${jwt.refresh-token-validity}") long refreshTokenValidity) {
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    this.accessTokenValidity = accessTokenValidity;
    this.refreshTokenValidity = refreshTokenValidity;
  }
  
  /** ✅ Access Token 생성 */
  public String generateAccessToken(String username) {
    return buildToken(username, accessTokenValidity);
  }
  
  /** ✅ Refresh Token 생성 */
  public String generateRefreshToken(String username) {
    return buildToken(username, refreshTokenValidity);
  }
  
  /** ✅ 토큰에서 username 추출 */
  public String getUsername(String token) {
    return parseClaims(token).getSubject();
  }
  
  /** ✅ 토큰 유효성 검사 */
  public boolean validateToken(String token) {
    try {
      parseClaims(token); // 만료, 변조 여부 확인
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }
  
  /** ✅ 토큰 생성 공통 로직 */
  private String buildToken(String username, long validity) {
    Date now = new Date();
    Date expiry = new Date(now.getTime() + validity);
    
    return Jwts.builder()
        .subject(username)              // 사용자 식별자 (보통 username)
        .issuedAt(now)                  // 발급 시각
        .expiration(expiry)             // 만료 시각
        .signWith(secretKey)
        .compact();
  }
  
  /** ✅ Claims 파싱 */
  private Claims parseClaims(String token) {
    return Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
}
