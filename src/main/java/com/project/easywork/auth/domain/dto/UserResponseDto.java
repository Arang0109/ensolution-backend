package com.project.easywork.auth.domain.dto;

import com.project.easywork.common.constant.Status;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDto {
  private Long userId;
  private String username;
  private String teamName;
  private String grade;
  private String department;
  private String name;
  private String email;
  private String phoneNumber;
  private String birthDate;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Status Status;
}