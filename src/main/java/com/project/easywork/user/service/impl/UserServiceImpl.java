package com.project.easywork.user.service.impl;

import com.project.easywork.auth.security.CustomUserDetails;
import com.project.easywork.user.validator.UserPasswordValidator;
import com.project.easywork.user.domain.dto.PasswordUpdateDto;
import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.user.domain.entity.User;
import com.project.easywork.user.mapper.UserMapper;
import com.project.easywork.user.service_data.IUserDataService;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.dto.UserUpdateDto;
import com.project.easywork.user.service.UserService;
import com.project.easywork.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
  
  private final UserValidator userValidator;
  private final UserPasswordValidator userPasswordValidator;
  private final PasswordEncoder passwordEncoder;
  
  private final IUserDataService userDataService;
  private final UserMapper userMapper;
  
  @Override
  public UserResponseDto register(UserCreateDto dto) {
    userValidator.validate(dto);
    User user = userMapper.toEntity(dto);
    user.changePassword(passwordEncoder.encode(dto.getPassword()));
    return userMapper.toDto(userDataService.save(user));
  }
  
  @Override
  @Transactional(readOnly = true)
  @PreAuthorize("hasRole('ADMIN')")
  public List<UserResponseDto> findAll() {
    return userDataService.findAll()
        .stream()
        .map(userMapper::toDto)
        .toList();
  }
  
  @Override
  @Transactional(readOnly = true)
  public UserResponseDto getProfileByUsername(String username) {
    return userMapper.toDto(userDataService.findByUsername(username));
  }
  
  @Override
  public UserResponseDto update(UserUpdateDto dto) {
    User user = getUserById(dto.getUserId());
    
    user.updateProfile(
        dto.getName(),
        dto.getEmail(),
        dto.getDepartment(),
        dto.getGrade(),
        dto.getPhoneNumber());
    
    return userMapper.toDto(userDataService.update(user));
  }
  
  @Override
  public UserResponseDto updatePassword(Long userId, PasswordUpdateDto dto) {
    User user = getUserById(userId);
    userPasswordValidator.validate(user, dto);
    user.changePassword(passwordEncoder.encode(dto.getNewPassword()));
    return userMapper.toDto(user);
  }
  
  @Override
  public void removeUser(CustomUserDetails userDetails) {
    Long userId = userDetails.getUser().getId();
    userDataService.deleteById(userId);
  }
  
  private User getUserById(Long userId) {
    return userDataService.findById(userId);
  }
}
