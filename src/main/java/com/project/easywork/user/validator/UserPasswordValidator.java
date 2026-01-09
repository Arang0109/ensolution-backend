package com.project.easywork.user.validator;

import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.user.domain.dto.PasswordUpdateD;
import com.project.easywork.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPasswordValidator {
  
  private final PasswordEncoder passwordEncoder;
  
  public void validate(User user, PasswordUpdateD dto) {
    if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
      throw new CustomException(ErrorCode.BAD_REQUEST, "현재 비밀번호가 올바르지 않습니다.");
    }
    
    if (!dto.getNewPassword().equals(dto.getConfirmNewPassword())) {
      throw new CustomException(ErrorCode.BAD_REQUEST, "새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    }
    
    if (passwordEncoder.matches(dto.getNewPassword(), user.getPassword())) {
      throw new CustomException(ErrorCode.BAD_REQUEST, "기존 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.");
    }
  }
}