package com.project.easywork.client.domain.dto.workplace;

import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "사업장 정보 응답 DTO", allOf = {WorkplaceDto.class})
public class WorkplaceProfileDto extends WorkplaceDto {
  private CompanyResponseDto company;
}
