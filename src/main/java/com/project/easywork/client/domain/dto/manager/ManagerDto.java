package com.project.easywork.client.domain.dto.manager;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ManagerDto {
  
  @Schema(
      description = "담당자 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long managerId;
  
  @Schema(description = "사엄장 ID (FK)", example = "1")
  @NotNull(message = "필수 입력")
  private Long workplaceId;
  
  @Schema(description = "담당자", example = "강민수")
  private String managerName;
  
  @Schema(description = "담당자 이메일", example = "test@test.com")
  private String email;
  
  @Schema(description = "담당자 연락처", example = "032-111-1111")
  private String telNumber;
  
  @Schema(description = "비고", example = "특이사항 없음")
  private String remark;
  
}
