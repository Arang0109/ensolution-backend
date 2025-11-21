package com.project.easywork.auth.service;

import com.project.easywork.auth.dto.UserCreateDto;
import com.project.easywork.auth.dto.UserResponseDto;
import com.project.easywork.auth.dto.UserUpdateDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
  List<UserResponseDto> findAll();
  @Transactional(readOnly = true) UserResponseDto getProfileByUsername(String username);
  @Transactional
  void register(UserCreateDto dto);
  @Transactional UserResponseDto update(UserUpdateDto dto);
}
