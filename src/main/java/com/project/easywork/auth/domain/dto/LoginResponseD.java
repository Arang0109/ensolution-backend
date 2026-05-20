package com.project.easywork.auth.domain.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
public class LoginResponseD {
  private String accessToken;
  private String username;
  private List<String> roles;
}
