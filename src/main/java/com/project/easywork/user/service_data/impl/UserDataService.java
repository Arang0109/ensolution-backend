package com.project.easywork.user.service_data.impl;

import com.project.easywork.user.domain.entity.User;
import com.project.easywork.user.repository.UserRepository;
import com.project.easywork.user.service_data.IUserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDataService implements IUserDataService {
  
  private final UserRepository userRepository;
  
  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }
  
  @Override
  public User save(User user) {
    return userRepository.save(user);
  }
  
  @Override
  public void deleteById(Long userId) {
    userRepository.deleteById(userId);
  }
  
  @Override
  public User findByUsername(String userName) {
    return userRepository.findByUsername(userName).orElseThrow();
  }
  
  @Override
  public User findById(Long userId) {
    return userRepository.findById(userId).orElseThrow();
  }
  
  @Override
  public List<User> findUsersByTeamId(Long id) {
    return userRepository.findUsersByTeamId(id);
  }
}
