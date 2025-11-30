package com.project.easywork.user.domain.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserUpdateDto {
  private Long userId;
  private Long teamId;
  private String grade;
  private String department;
  private String name;
  private String email;
  private String phoneNumber;
  private LocalDate birthDate;
}
