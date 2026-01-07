package com.project.easywork.client.domain.dto.company;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CompanyUpdateD {
  private String name;
  private String address;
  private String ceoName;
  @Pattern(regexp = "^\\d{10}$", message = "사업자번호는 10자리 숫자여야 합니다.")
  private String bizNumber;
  private String remark;
}