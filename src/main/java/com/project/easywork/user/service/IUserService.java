package com.project.easywork.user.service;

import com.project.easywork.auth.security.user.CustomUserDetails;
import com.project.easywork.user.domain.dto.PasswordUpdateD;
import com.project.easywork.user.domain.dto.UserCreateD;
import com.project.easywork.user.domain.dto.UserD;
import com.project.easywork.user.domain.dto.UserUpdateD;

import java.util.List;

public interface IUserService {
  List<UserD> findAll();
  UserD getProfileByUsername(String username);
  UserD register(UserCreateD dto);
  UserD update(Long userId, UserUpdateD dto);
  void removeUser(CustomUserDetails userDetails);
  UserD updatePassword(Long userId, PasswordUpdateD dto);
  UserD updateTeam(Long userId, Long teamId);
}
