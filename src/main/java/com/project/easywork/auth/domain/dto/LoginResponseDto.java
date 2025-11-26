package com.project.easywork.auth.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponseDto {
  private String accessToken;
  private String username;
}
