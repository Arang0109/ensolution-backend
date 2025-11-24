package com.project.easywork.user.service;

import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.user.domain.dto.UserResponseDto;
import com.project.easywork.user.domain.dto.UserUpdateDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
  List<UserResponseDto> findAll();
  @Transactional(readOnly = true) UserResponseDto getProfileByUsername(String username);
  @Transactional
  void register(UserCreateDto dto);
  @Transactional UserResponseDto update(UserUpdateDto dto);
}
