package com.project.easywork.auth.service.impl;

import com.project.easywork.auth.domain.dto.UserCreateDto;
import com.project.easywork.auth.service_data.UserDataService;
import com.project.easywork.auth.domain.dto.UserResponseDto;
import com.project.easywork.auth.domain.dto.UserUpdateDto;
import com.project.easywork.auth.service.UserService;
import com.project.easywork.auth.service.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  
  private final UserValidator userValidator;
  private final UserDataService userDataService;
  
  @Override
  public void register(UserCreateDto dto) {
    userValidator.validate(dto);
    userDataService.save(dto);
  }
  
  @Override
  public List<UserResponseDto> findAll() {
    return userDataService.findAll();
  }
  
  @Override
  public UserResponseDto getProfileByUsername(String username) {
    return userDataService.findByUsername(username);
  }
  
  @Override
  public UserResponseDto update(UserUpdateDto dto) {
    return userDataService.update(dto);
  }
}
