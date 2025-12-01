package com.project.easywork.user.service_data;

import com.project.easywork.user.domain.entity.User;

import java.util.List;

public interface UserDataService {
  List<User> findAll();
  User save(User user);
  User update(User user);
  User findByUsername(String username);
  User findById(Long userId);
}