package com.project.easywork.client.domain.dto.workplace;

import com.project.easywork.common.constant.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkplaceRegisterDto {
  @Schema(description = "측정대상 사업장", example = "테스트 사업장")
  @NotBlank(message = "필수 입력")
  private String workplaceName;
  
  @Schema(description = "측정대행 의뢰업체", example = "테스트 의뢰업체")
  @NotBlank(message = "필수 입력")
  private String companyName;
  
  @Schema(description = "대표자", example = "홍길동")
  @NotBlank(message = "필수 입력")
  private String ceoName;
  
  @Schema(description = "사업자번호", example = "4251701564")
  @NotBlank(message = "필수 입력")
  @Pattern(regexp = "^\\d{10}$", message = "사업자번호는 10자리 숫자여야 합니다.")
  private String bizNumber;
  
  @Schema(description = "사업장 주소", example = "부산 진구 엄광로 384")
  private String address;
  
  @Schema(description = "업종", example = "자동차 제조업")
  private String businessCategory;
  
  @Schema(
      description = "사업장 종별 (TYPE_1=1종, TYPE_2=2종, TYPE_3=3종, TYPE_4=4종, TYPE_5=5종)",
      example = "TYPE_1"
  )
  private Size workplaceSize;
  
  @Schema(description = "담당자", example = "담당자 OOO")
  private String managerName;
  
  @Schema(description = "전화번호", example = "05112345678")
  private String telNumber;
  
  @Schema(description = "이메일", example = "test@test.com")
  @Email(message = "올바른 이메일 형식을 입력해주세요.")
  private String email;
  
  @Schema(description = "비고", example = "특이사항 없음")
  private String remark;
}