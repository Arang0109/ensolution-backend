package com.project.easywork.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.easywork.user.domain.Active;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
public class UserCreateDto {
  @Schema(description = "팀 ID (FK)")
  private Long teamId;
  
  @Schema(description = "회원 아이디", example = "test01")
  @NotBlank(message = "필수 입력")
  @Size(min = 4, max = 20, message = "아이디는 4~20자여야 합니다.")
  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 사용할 수 있습니다.")
  private String username;
  
  @Schema(description = "비밀번호", example = "test!123")
  @NotBlank(message = "필수 입력")
  @Size(min = 8, max = 20, message = "비밀번호는 8~20자여야 합니다.")
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,20}$",
      message = "비밀번호는 영문, 숫자, 특수문자를 모두 포함해야 합니다.")
  private String password;
  
  @Schema(description = "직급", example = "사원")
  private String grade;
  
  @Schema(description = "부서", example = "자가측정부")
  private String department;
  
  @Schema(description = "회원 이름", example = "강민수")
  @NotBlank(message = "필수 입력")
  private String name;
  
  @Schema(description = "이메일", example = "test@test.com")
  @NotBlank(message = "필수 입력")
  @Email(message = "올바른 이메일 형식을 입력해주세요.")
  private String email;
  
  @Schema(description = "휴대폰 번호", example = "01055152835")
  @NotBlank(message = "필수 입력")
  @Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 10~11자리 숫자여야 합니다.")
  private String phoneNumber;
  
  @Schema(description = "생년월일", example = "1995-02-09")
  @NotNull(message = "필수 입력")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate birthDate;
  
  @Schema(
    description = "생성일", example = "2025-09-26 01:24:12",
    accessMode =Schema.AccessMode.READ_ONLY)
  private LocalDateTime createdAt;
  
  @Schema(
      description = "수정일", example = "2025-09-26 01:24:12",
      accessMode =Schema.AccessMode.READ_ONLY)
  private LocalDateTime modifiedAt;
  
  @Schema(
      description = "상태", example = "ACTIVE",
      accessMode =Schema.AccessMode.READ_ONLY
  )
  private Active active = Active.ACTIVE;
}
