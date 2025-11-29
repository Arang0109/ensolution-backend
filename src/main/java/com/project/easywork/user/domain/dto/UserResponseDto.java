package com.project.easywork.user.domain.dto;

import lombok.*;

import java.time.LocalDate;
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
  private LocalDate birthDate;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}