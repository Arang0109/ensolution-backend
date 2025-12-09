package com.project.easywork.auth.service;

import com.project.easywork.auth.security.CustomUserDetails;
import com.project.easywork.user.domain.entity.User;
import com.project.easywork.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  
  private final UserRepository userRepository;
  
  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByUsernameWithRoles(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
    
    return new CustomUserDetails(user);
  }
}
