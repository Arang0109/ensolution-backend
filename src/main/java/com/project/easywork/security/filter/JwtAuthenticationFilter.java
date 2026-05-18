package com.project.easywork.security.filter;

import com.project.easywork.security.user.CustomUserDetailsService;
import com.project.easywork.security.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  
  private final JwtTokenProvider jwtTokenProvider;
  private final CustomUserDetailsService customUserDetailsService;
  
  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    
    try {
      String header = request.getHeader("Authorization");
      
      if (header != null && header.startsWith("Bearer ")) {
        String token = header.substring(7);
        
        if (jwtTokenProvider.validateToken(token)) {
          String username = jwtTokenProvider.getUsername(token);
          UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
      
      filterChain.doFilter(request, response);
    } catch (RuntimeException e) {
      SecurityContextHolder.clearContext();
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write("{\"success\":false, \"message\":\"" + e.getMessage() + "\"}");
    }
    
  }
  
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getServletPath();
    return path.startsWith("/api/auth/refresh") || path.startsWith("/api/auth/login");
  }
}
