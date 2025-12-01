package com.project.easywork.client.domain.dto.facility;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class FacilityResponseDto {
  
  @Schema(
      description = "배출시설 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private Long preventionId;
  private String name;
  private String fuelUsage;
  private String itemOutput;
  private String fuelInput;
  private String fuelType;
  private String remark;
  
  @Schema(description = "생성날짜") private LocalDate createdAt;
  @Schema(description = "수정날짜") private LocalDate modifiedAt;
}