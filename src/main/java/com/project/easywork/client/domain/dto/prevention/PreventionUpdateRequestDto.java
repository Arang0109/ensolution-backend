package com.project.easywork.client.domain.dto.prevention;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PreventionUpdateRequestDto {
  private String name;
  private String remark;
}
