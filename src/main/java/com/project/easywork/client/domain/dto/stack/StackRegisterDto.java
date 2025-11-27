package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.common.constant.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackRegisterDto {
  @Schema(description = "측정공", example = "stack_000")
  @NotBlank(message = "필수 입력")
  private String stackName;
  
  @Schema(description = "SEMS 번호", example = "000")
  @NotBlank(message = "필수 입력")
  private String semsNumber;
  
  @Schema(
      description = "배출시설 종별 (TYPE_1=1종, TYPE_2=2종, TYPE_3=3종, TYPE_4=4종, TYPE_5=5종)",
      example = "TYPE_1"
  )
  private Size stackSize;
  
  @Schema(description = "측정공 높이", example = "3m")
  private String stackHeight;
  
  @Schema(description = "비고", example = "특이사항 없음")
  private String remark;
  
  @Schema(description = "사업장 ID (FK)", example = "1")
  @NotNull(message = "필수 입력")
  private Long workplaceId;
}