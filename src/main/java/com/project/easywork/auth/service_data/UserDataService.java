package com.project.easywork.auth.service_data;

import com.project.easywork.auth.dto.UserCreateDto;
import com.project.easywork.auth.dto.UserResponseDto;
import com.project.easywork.auth.dto.UserUpdateDto;

import java.util.List;

public interface UserDataService {
  List<UserResponseDto> findAll();
  void save(UserCreateDto dto);
  UserResponseDto update(UserUpdateDto dto);
  UserResponseDto findByUsername(String username);
}