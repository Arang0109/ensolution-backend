package com.project.easywork.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
  @Schema(description = "휴대폰 번호", example = "01055152835")
  @Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 10~11자리 숫자여야 합니다.")
  private String phoneNumber;
  @Schema(description = "생년월일", example = "1995-02-09")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate birthDate;
}
