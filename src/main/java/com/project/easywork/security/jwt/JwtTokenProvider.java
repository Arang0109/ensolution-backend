package com.project.easywork.security.jwt;

import com.project.easywork.security.domain.JwtProperties;
import com.project.easywork.security.domain.JwtToken;
import com.project.easywork.security.user.CustomUserDetails;
import com.project.easywork.security.user.CustomUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtTokenProvider {
  private final long AT_VALID;
  private final long RT_VALID;
  private final SecretKey SECRET_KEY;
  
  private final CustomUserDetailsService customUserDetailsService;
  
  public JwtTokenProvider(
      JwtProperties jwtProperties,
      CustomUserDetailsService customUserDetailsService) {
    this.SECRET_KEY = Keys.hmacShaKeyFor(jwtProperties.secret().getBytes());
    this.AT_VALID = jwtProperties.accessTokenValidity();
    this.RT_VALID = jwtProperties.refreshTokenValidity();
    this.customUserDetailsService = customUserDetailsService;
  }
  
  public JwtToken createToken(Authentication authentication) {
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();
    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)  // "ROLE_ADMIN"
        .map(auth -> auth.replace("ROLE_", "")) // "ADMIN" (Optional)
        .toList();
    
    return new JwtToken(
        "Bearer",
        userDetails.getUsername(),
        buildToken(username, AT_VALID),
        buildToken(username, RT_VALID),
        roles);
  }
  
  public String createAccessToken(Authentication authentication) {
    String username = authentication.getName();
    return buildToken(username, AT_VALID);
  }
  
  public Authentication getAuthentication(String token) {
    String username = parseClaims(token).getSubject();
    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
    
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }
  
  public boolean validateToken(String token) {
    try {
      parseClaims(token); // 만료, 변조 여부 확인
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }
  
  private String buildToken(String username, long validity) {
    Date now = new Date();
    Date expiry = new Date(now.getTime() + validity);
    
    return Jwts.builder()
        .subject(username)              // 사용자 식별자 (보통 username)
        .issuedAt(now)                  // 발급 시각
        .expiration(expiry)             // 만료 시각
        .signWith(SECRET_KEY)
        .compact();
  }
  
  private Claims parseClaims(String token) {
    return Jwts.parser()
        .verifyWith(SECRET_KEY)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
}