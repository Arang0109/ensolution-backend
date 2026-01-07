package com.project.easywork.auth.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginRequestD {
  @Schema(description = "회원 아이디", example = "kmsq321")
  private String username;
  @Schema(description = "비밀번호", example = "rkdalstn!12")
  private String password;
}