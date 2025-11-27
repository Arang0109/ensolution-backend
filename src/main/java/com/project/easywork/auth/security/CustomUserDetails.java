package com.project.easywork.auth.security;

import com.project.easywork.user.domain.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {
  private final Long userId;
  private final String username;
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;
  
  public CustomUserDetails(User user) {
    this.userId = user.getId();
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.authorities = Collections.emptyList();
  }
  
  @Override
  public String getUsername() {
    return username;
  }
  
  @Override
  public String getPassword() {
    return password;
  }
  
  // isAccountNonExpired, isAccountNonLocked 등은 생략
}
