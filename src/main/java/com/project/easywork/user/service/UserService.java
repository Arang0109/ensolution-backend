package com.project.easywork.user.service;

import com.project.easywork.user.domain.dto.PasswordUpdateDto;
import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
  List<UserResponseDto> findAll();
  UserResponseDto getProfileByUsername(String username);
  UserResponseDto register(UserCreateDto dto);
  UserResponseDto update(UserUpdateDto dto);
  UserResponseDto updatePassword(Long userId, PasswordUpdateDto dto);
}
