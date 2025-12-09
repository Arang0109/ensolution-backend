package com.project.easywork.auth.domain.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponseDto {
  private String accessToken;
  private String username;
  private List<String> roles;
}
