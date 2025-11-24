package com.project.easywork.user.domain.dto;

import com.project.easywork.common.constant.Status;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserUpdateDto {
  private Long userId;
  private String username;
  private String password;
  private String grade;
  private String department;
  private String name;
  private String email;
  private String phoneNumber;
  private String birthDate;
  private Status status;
}
