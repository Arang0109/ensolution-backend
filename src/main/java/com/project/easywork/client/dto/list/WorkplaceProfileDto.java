package com.project.easywork.client.dto.list;

import com.project.easywork.client.dto.CompanyDto;
import com.project.easywork.client.dto.WorkplaceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "사업장 정보 응답 DTO", allOf = {WorkplaceDto.class})
public class WorkplaceProfileDto extends WorkplaceDto {
  private CompanyDto company;
}
