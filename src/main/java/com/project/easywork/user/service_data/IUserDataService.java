package com.project.easywork.user.service_data;

import com.project.easywork.user.domain.entity.User;

import java.util.List;

public interface IUserDataService {
  List<User> findAll();
  User save(User user);
  void deleteById(Long userId);
  User findByUsername(String username);
  User findById(Long userId);
  List<User> findUsersByTeamId(Long id);
}