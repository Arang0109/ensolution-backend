package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.common.constant.Grade;
import com.project.easywork.common.constant.Shape;
import com.project.easywork.common.constant.StackType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StackDto {
  
  @Schema(
      description = "측정공 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long stackId;
  
  @Schema(description = "사업장 ID (FK)", example = "1")
  @NotNull(message = "필수 입력")
  private Long workplaceId;
  
  @Schema(description = "측정공", example = "stack_000")
  @NotBlank(message = "필수 입력")
  private String stackName;
  
  @Schema(description = "SEMS 번호", example = "000")
  private String semsNumber;
  
  @Schema(
      description = "배출시설 종별 (TYPE_1=1종, TYPE_2=2종, TYPE_3=3종, TYPE_4=4종, TYPE_5=5종)",
      example = "TYPE_1"
  )
  private Grade stackGrade;
  
  @Schema(description = "측정공 높이", example = "3.0")
  private Double stackHeight;
  
  @Schema(description = "연도 가로 길이(m)", example = "1.400")
  private Double horizontalLength;
  
  @Schema(description = "연도 세로 길이(m)", example = "1.400")
  private Double verticalLength;
  
  @Schema(description = "연도 모양 (원형, 사각형, 기타)", example = "CIRCULAR")
  private Shape shape;
  
  @Schema(description = "연도 타입 (수직, 수평)", example = "VERTICAL")
  private StackType stackType;
  
  @Schema(description = "비고", example = "특이사항 없음")
  private String remark;
}