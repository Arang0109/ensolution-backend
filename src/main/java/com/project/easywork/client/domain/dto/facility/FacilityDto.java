package com.project.easywork.client.domain.dto.facility;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FacilityDto {
  
  @Schema(
      description = "배출시설 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long facilityId;
  
  @Schema(description = "방지시설 ID (FK)", example = "1")
  @NotNull(message = "필수 입력")
  private Long preventionId;
  
  @Schema(description = "배출시설", example = "도장시설(데드너)")
  @NotBlank(message = "필수 입력")
  private String facilityName;
  
  @Schema(description = "연료 사용량", example = "000")
  private String fuelUsage;
  
  @Schema(description = "제품 생산량", example = "000")
  private String itemOutput;
  
  @Schema(description = "연료 투입량", example = "000")
  private String fuelInput;
  
  @Schema(description = "연료", example = "도료")
  private String fuelType;
  
  @Schema(description = "비고", example = "특이사항 없음")
  private String remark;
}