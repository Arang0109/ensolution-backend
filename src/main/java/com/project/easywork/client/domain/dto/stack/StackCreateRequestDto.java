package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StackCreateRequestDto {
  @Schema(description = "측정공", example = "stack_000")
  @NotBlank(message = "필수 입력")
  private String name;
  
  @Schema(description = "사업장 ID (FK)", example = "1")
  @NotNull(message = "필수 입력")
  private Long workplaceId;
  
  @Schema(description = "SEMS 번호", example = "000")
  @NotBlank(message = "필수 입력")
  private String semsNumber;
  
  @Schema(
      description = "측정시설 종별 (TYPE_1=1종, TYPE_2=2종, TYPE_3=3종, TYPE_4=4종, TYPE_5=5종)",
      example = "TYPE_1"
  )
  private Grade grade;
  
  @Schema(description = "측정공 높이", example = "15.5")
  @Min(value = 0, message = "0 이상의 값을 입력해주세요.")
  private Double height;
  
  @Schema(description = "가로 길이", example = "1.2")
  @Min(value = 0, message = "0 이상의 값을 입력해주세요.")
  private Double horizontalLength;
  
  @Schema(description = "세로 길이", example = "1.5")
  @Min(value = 0, message = "0 이상의 값을 입력해주세요.")
  private Double verticalLength;
  
  @Schema(description = "측정시설 모양")
  private Shape shape;
  
  @Schema(description = "측정시설 방향")
  private Orientation orientation;
  
  @Schema(description = "표준산소 농도(%)")
  @Min(value = 0, message = "0 이상의 값을 입력해주세요.")
  @Max(value = 21, message = "21 이하의 값을 입력해주세요.")
  private Double standardOxygen;
  
  @Schema(description = "비고", example = "특이사항 없음")
  private String remark;
}