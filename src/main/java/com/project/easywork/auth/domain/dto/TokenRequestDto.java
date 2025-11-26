package com.project.easywork.auth.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TokenRequestDto {
  private String refreshToken;
}
