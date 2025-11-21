package com.project.easywork.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyDto {
  
  @Schema(
      description = "의뢰업체 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long companyId;
  
  @Schema(description = "측정대행 의뢰업체", example = "테스트 의뢰업체")
  @NotBlank(message = "필수 입력")
  private String companyName;
  
  @Schema(description = "의뢰업체 주소", example = "부산 진구 엄광로 384")
  private String address;
  
  @Schema(description = "대표자", example = "홍길동")
  @NotBlank(message = "필수 입력")
  private String ceoName;
  
  @Schema(description = "사업자번호", example = "4251701564")
  @NotBlank(message = "필수 입력")
  @Pattern(regexp = "^\\d{10}$", message = "사업자번호는 10자리 숫자여야 합니다.")
  private String bizNumber;
  
  @Schema(description = "비고", example = "특이사항 없음")
  private String remark;
}