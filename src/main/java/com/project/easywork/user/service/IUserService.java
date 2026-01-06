package com.project.easywork.user.service;

import com.project.easywork.auth.security.CustomUserDetails;
import com.project.easywork.user.domain.dto.PasswordUpdateDto;
import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.dto.UserUpdateDto;

import java.util.List;

public interface IUserService {
  List<UserResponseDto> findAll();
  UserResponseDto getProfileByUsername(String username);
  UserResponseDto register(UserCreateDto dto);
  UserResponseDto update(Long userId, UserUpdateDto dto);
  void removeUser(CustomUserDetails userDetails);
  UserResponseDto updatePassword(Long userId, PasswordUpdateDto dto);
  UserResponseDto updateTeam(Long userId, Long teamId);
}
