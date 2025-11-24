package com.project.easywork.auth.service_data.impl;

import com.project.easywork.auth.domain.dto.UserCreateDto;
import com.project.easywork.auth.domain.dto.UserResponseDto;
import com.project.easywork.auth.domain.dto.UserUpdateDto;
import com.project.easywork.auth.mapper.UserMapper;
import com.project.easywork.auth.service_data.UserDataService;
import com.project.easywork.auth.domain.entity.User;
import com.project.easywork.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {
  
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  
  @Override
  @Transactional
  public List<UserResponseDto> findAll() {
    return userMapper.toResponseDtoList(userRepository.findAll());
  }
  
  @Override
  @Transactional
  public void save(UserCreateDto dto) {
    User user = userMapper.toEntityForCreate(dto);
    user.changePassword(passwordEncoder.encode(dto.getPassword()));
    userRepository.save(user);
  }
  
  @Override
  @Transactional
  public UserResponseDto update(UserUpdateDto dto) {
    User user = userRepository.findById(dto.getUserId()).orElseThrow();
    
    user.updateProfile(
        dto.getName(),
        dto.getEmail(),
        dto.getDepartment(),
        dto.getGrade(),
        dto.getPhoneNumber());
    
    if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
      user.changePassword(passwordEncoder.encode(dto.getPassword()));
    }
    
    return userMapper.toResponseDto(user);
  }
  
  @Override
  public UserResponseDto findByUsername(String userName) {
    return userMapper.toResponseDto(
        userRepository.findByUsername(userName).orElseThrow()
    );
  }
}
