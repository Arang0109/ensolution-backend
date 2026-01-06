package com.project.easywork.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserUpdateDto {
  private String grade;
  private String department;
  private String name;
  private String email;
  @Schema(description = "휴대폰 번호", example = "01055152835")
  @Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 10~11자리 숫자여야 합니다.")
  private String phoneNumber;
}
