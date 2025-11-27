package com.project.easywork.client.domain.dto.prevention;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PreventionDto {
  @Schema(
      description = "방지시설 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  @Schema(description = "배출구 ID (FK)", example = "1")
  @NotNull(message = "필수 입력")
  private Long stackId;
  
  @Schema(description = "방지시설", example = "세정집진시설")
  @NotBlank(message = "필수 입력")
  private String name;
  
  @Schema(description = "비고", example = "특이사항 없음")
  private String remark;
}
