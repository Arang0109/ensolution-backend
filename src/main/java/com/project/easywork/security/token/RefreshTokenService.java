package com.project.easywork.security.token;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
  
  private final StringRedisTemplate redisTemplate;
  
  // Refresh Token 저장
  public void saveRefreshToken(String username, String refreshToken, long duration, TimeUnit unit) {
    String key = "RT:" + username; // key 네이밍 규칙
    redisTemplate.opsForValue().set(key, refreshToken, duration, unit);
  }
  
  // Refresh Token 조회
  public String getRefreshToken(String username) {
    return redisTemplate.opsForValue().get("RT:" + username);
  }
  
  // Refresh Token 삭제 (로그아웃 시)
  public void deleteRefreshToken(String username) {
    redisTemplate.delete("RT:" + username);
  }
}