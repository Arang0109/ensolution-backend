package com.project.easywork.client.domain.dto.workplace;

import com.project.easywork.common.constant.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkplaceDto {
  
  @Schema(
      description = "사업장 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long workplaceId;
  
  @Schema(description = "의뢰업체 ID (FK)")
  @NotNull(message = "필수 입력")
  private Long companyId;
  
  @Schema(description = "측정대상 사업장", example = "테스트 사업장")
  @NotBlank(message = "필수 입력")
  private String workplaceName;
  
  @Schema(description = "사업장 주소", example = "부산 진구 엄광로 384")
  private String address;
  
  @Schema(description = "사업자번호", example = "4251701564")
  @NotBlank(message = "필수 입력")
  @Pattern(regexp = "^\\d{10}$", message = "사업자번호는 10자리 숫자여야 합니다.")
  private String bizNumber;
  
  @Schema(description = "업종", example = "자동차 제조업")
  private String businessCategory;
  
  @Schema(
      description = "사업장 종별 (TYPE_1=1종, TYPE_2=2종, TYPE_3=3종, TYPE_4=4종, TYPE_5=5종)",
      example = "TYPE_1"
  )
  private Size workplaceSize;
  
  @Schema(description = "비고", example = "특이사항 없음")
  private String remark;
}