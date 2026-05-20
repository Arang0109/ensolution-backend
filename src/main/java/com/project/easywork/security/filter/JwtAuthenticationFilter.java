package com.project.easywork.security.filter;

import com.project.easywork.security.handler.CustomAuthenticationFailureHandler;
import com.project.easywork.security.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  
  private final CustomAuthenticationFailureHandler authenticationFailureHandler;
  private final JwtTokenProvider jwtTokenProvider;
  
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
    try {
      String header = request.getHeader("Authorization");
      
      if (header != null && header.startsWith("Bearer ")) {
        String token = header.substring(7);
        
        if (jwtTokenProvider.validateToken(token)) {
          Authentication authentication = jwtTokenProvider.getAuthentication(token);
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
      
      filterChain.doFilter(request, response);
    } catch (AuthenticationException e) {
      SecurityContextHolder.clearContext();
      
      authenticationFailureHandler.onAuthenticationFailure(
          request, response,
          new BadCredentialsException(e.getMessage(), e)
      );
    }
    
  }
  
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getServletPath();
    return path.startsWith("/api/auth/refresh") || path.startsWith("/api/auth/login");
  }
}
