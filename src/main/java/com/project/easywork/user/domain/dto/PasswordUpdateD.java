package com.project.easywork.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PasswordUpdateD {
  @NotBlank(message = "현재 비밀번호는 필수 입력입니다.")
  private String currentPassword;
  
  @Schema(description = "새 비밀번호", example = "test!123")
  @NotBlank(message = "새 비밀번호는 필수 입력입니다.")
  @Size(min = 8, max = 20, message = "비밀번호는 8~20자여야 합니다.")
  @Pattern(
      regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,20}$",
      message = "비밀번호는 영문, 숫자, 특수문자를 모두 포함해야 합니다."
  )
  private String newPassword;
  
  @NotBlank(message = "새 비밀번호 확인은 필수 입력입니다.")
  private String confirmNewPassword;
}
