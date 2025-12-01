package com.project.easywork.client.validator;

import com.project.easywork.user.domain.dto.UserCreateDto;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
  
  private final UserRepository userRepository;
  
  public void validate(UserCreateDto dto) {
    validateDuplicateEmail(dto.getEmail());
    validateDuplicatePhoneNumber(dto.getPhoneNumber());
    validateDuplicateUsername(dto.getUsername());
  }
  
  private void validateDuplicateEmail(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new CustomException(ErrorCode.CONFLICT, "이미 사용 중인 이메일입니다.");
    }
  }
  
  private void validateDuplicatePhoneNumber(String phoneNumber) {
    if (userRepository.existsByPhoneNumber(phoneNumber)) {
      throw new CustomException(ErrorCode.CONFLICT, "이미 등록된 휴대폰 번호입니다.");
    }
  }
  
  private void validateDuplicateUsername(String username) {
    if (userRepository.existsByUsername(username)) {
      throw new CustomException(ErrorCode.CONFLICT, "이미 등록된 사용자명입니다.");
    }
  }
}

