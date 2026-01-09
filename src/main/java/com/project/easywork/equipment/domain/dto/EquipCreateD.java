package com.project.easywork.equipment.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EquipCreateD {
  
  @Schema(
      description = "장비 관리번호(회사 내부에서 사용하는 고유 번호)",
      example = "EQ-2025-001"
  )
  @NotNull(message = "장비 관리번호는 필수 값입니다.")
  private String managementNumber;
  
  @Schema(
      description = "장비 시리얼번호(제조사 고유 번호)",
      example = "SN-A1B2C3D4"
  )
  private String serialNumber;
  
  @Schema(
      description = "장비 모델명",
      example = "Testo 350"
  )
  @NotNull(message = "장비 모델명은 필수 값입니다.")
  private String modelName;
  
  @Schema(
      description = "장비명 또는 커스텀 명칭",
      example = "배출가스 분석기 1호"
  )
  private String equipmentName;
  
  @Schema(
      description = "장비 가격",
      example = "1250000"
  )
  @Min(value = 0, message = "0 이상의 값을 입력해주세요.")
  private BigDecimal price;
  
  @Schema(
      description = "제조사",
      example = "Testo"
  )
  private String manufacturer;
  
  @Schema(
      description = "제조 국가(원산지)",
      example = "독일"
  )
  private String originCountry;
  
  @Schema(
      description = "구입일자 (YYYY-MM-DD)",
      example = "2024-05-10"
  )
  private LocalDate purchaseDate;
  
  @Schema(
      description = "교정이 수행된 날짜 (YYYY-MM-DD)",
      example = "2025-01-15"
  )
  private LocalDate calibrationDate;
  
  @Schema(
      description = "교정 주기(월 단위)",
      example = "12"
  )
  @Size(min = 1, max = 12, message = "1~36 사이의 값을 입력해주세요.")
  private Integer calibrationCycle;
  
  @Schema(
      description = "비고 또는 참고사항",
      example = "연 1회 교정, 2024년부터 신규 도입 장비"
  )
  private String remark;
}
