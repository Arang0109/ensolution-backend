package com.project.easywork.auth.service;

import com.project.easywork.auth.security.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthTokenService {
  
  private final StringRedisTemplate redisTemplate;
  
  // Refresh Token 저장
  public void saveRefreshToken(String username, String refreshToken, long duration) {
    String key = "RT:" + username; // key 네이밍 규칙
    redisTemplate.opsForValue().set(key, refreshToken, duration, TimeUnit.MILLISECONDS);
  }
  
  // Refresh Token 조회
  public String getRefreshToken(Authentication authentication) {
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();
    return redisTemplate.opsForValue().get("RT:" + username);
  }
  
  // Refresh Token 삭제 (로그아웃 시)
  public void deleteRefreshToken(String username) {
    redisTemplate.delete("RT:" + username);
  }
}