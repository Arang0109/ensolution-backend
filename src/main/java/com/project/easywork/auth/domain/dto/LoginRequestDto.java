package com.project.easywork.auth.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequestDto {
  @Schema(description = "회원 아이디", example = "test01")
  private String username;
  @Schema(description = "비밀번호", example = "test!123")
  private String password;
}