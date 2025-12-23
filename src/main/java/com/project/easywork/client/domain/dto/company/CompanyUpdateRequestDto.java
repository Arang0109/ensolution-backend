package com.project.easywork.client.domain.dto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUpdateRequestDto {
  private String name;
  private String address;
  private String ceoName;
  private String bizNumber;
  private String remark;
}