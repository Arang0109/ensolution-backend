package com.project.easywork.user.service_data;

import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.dto.UserUpdateDto;

import java.util.List;

public interface UserDataService {
  List<UserResponseDto> findAll();
  void save(UserCreateDto dto);
  UserResponseDto update(UserUpdateDto dto);
  UserResponseDto findByUsername(String username);
}