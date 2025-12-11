package com.project.easywork.equipment.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EquipmentCalibrationCreateReqDto {
  @Schema(
      description = "교정을 등록할 장비의 ID",
      example = "1"
  )
  private Long equipmentId;
  
  @Schema(
      description = "교정이 수행된 날짜 (YYYY-MM-DD)",
      example = "2025-01-15"
  )
  private LocalDate calibrationDate;
  
  @Schema(
      description = "교정 기관명",
      example = "한국표준과학연구원(KRISS)"
  )
  private String agency;
  
  @Schema(
      description = "교정 비용",
      example = "200000"
  )
  private BigDecimal cost;
  
  @Schema(
      description = "다음 교정 예정일 (YYYY-MM-DD)",
      example = "2026-01-15"
  )
  private LocalDate nextCalibrationDate;
  
  @Schema(
      description = "교정 증명서 파일 URL",
      example = "https://example.com/certificates/cali-2025-001.pdf"
  )
  private String certificateUrl;
  
  @Schema(
      description = "비고 또는 추가 메모",
      example = "NOx 측정값 보정 1.02 적용, 이상 없음"
  )
  private String remark;
}
