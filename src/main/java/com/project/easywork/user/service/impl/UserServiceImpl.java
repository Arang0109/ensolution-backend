package com.project.easywork.user.service.impl;

import com.project.easywork.client.validator.UserPasswordValidator;
import com.project.easywork.user.domain.dto.PasswordUpdateDto;
import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.user.domain.entity.User;
import com.project.easywork.user.mapper.UserMapper;
import com.project.easywork.user.service_data.UserDataService;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.dto.UserUpdateDto;
import com.project.easywork.user.service.UserService;
import com.project.easywork.client.validator.UserValidator;
import lombok.RequiredArgsConstructor;
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
  
  private final UserDataService userDataService;
  private final UserMapper userMapper;
  
  @Override
  public UserResponseDto register(UserCreateDto dto) {
    userValidator.validate(dto);
    User user = userMapper.toEntityForCreate(dto);
    user.changePassword(passwordEncoder.encode(dto.getPassword()));
    return userMapper.toResponseDto(userDataService.save(user));
  }
  
  @Override
  public List<UserResponseDto> findAll() {
    return userMapper.toResponseDtoList(userDataService.findAll());
  }
  
  @Override
  public UserResponseDto getProfileByUsername(String username) {
    return userMapper.toResponseDto(userDataService.findByUsername(username));
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
    
    return userMapper.toResponseDto(userDataService.update(user));
  }
  
  @Override
  public UserResponseDto updatePassword(Long userId, PasswordUpdateDto dto) {
    User user = getUserById(userId);
    userPasswordValidator.validate(user, dto);
    user.changePassword(passwordEncoder.encode(dto.getNewPassword()));
    return userMapper.toResponseDto(user);
  }
  
  private User getUserById(Long userId) {
    return userDataService.findById(userId);
  }
}
