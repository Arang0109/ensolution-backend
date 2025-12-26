package com.project.easywork.client.domain.dto.workplace;

import com.project.easywork.client.domain.Grade;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkplaceUpdateRequestDto {
  private String name;
  private String address;
  @Pattern(regexp = "^\\d{10}$", message = "사업자번호는 10자리 숫자여야 합니다.")
  private String bizNumber;
  private String businessCategory;
  private Grade grade;
  private String remark;
}
