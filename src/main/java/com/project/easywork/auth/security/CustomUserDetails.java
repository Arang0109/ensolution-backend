package com.project.easywork.auth.security;

import com.project.easywork.user.domain.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class CustomUserDetails implements UserDetails {
  private final User user;
  
  public CustomUserDetails(User user) {
    this.user = user;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getRoles().stream()
        .flatMap(role -> {
          // ROLE_ prefix required
          Stream<SimpleGrantedAuthority> roleAuth =
              Stream.of(new SimpleGrantedAuthority(role.getName()));
          
          // Role → Privilege 변환 (READ/WRITE 등)
          Stream<SimpleGrantedAuthority> privilegeAuth =
              role.getPrivileges().stream()
                  .map(p -> new SimpleGrantedAuthority(p.getName()));
          
          return Stream.concat(roleAuth, privilegeAuth);
        })
        .collect(Collectors.toSet());
  }
  
  @Override public String getUsername() { return user.getUsername(); }
  @Override public String getPassword() { return user.getPassword(); }
  
  @Override public boolean isAccountNonExpired() { return true; }
  @Override public boolean isAccountNonLocked() { return true; }
  @Override public boolean isCredentialsNonExpired() { return true; }
}
