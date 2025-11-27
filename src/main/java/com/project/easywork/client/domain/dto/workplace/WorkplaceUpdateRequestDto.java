package com.project.easywork.client.domain.dto.workplace;

import com.project.easywork.common.constant.Grade;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class WorkplaceUpdateRequestDto {
  private String name;
  private String address;
  private String bizNumber;
  private String businessCategory;
  private Grade grade;
  private String remark;
}
