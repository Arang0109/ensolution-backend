package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.common.constant.Grade;
import com.project.easywork.common.constant.Shape;
import com.project.easywork.common.constant.Orientation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class StackResponseDto {
  @Schema(
      description = "측정공 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private Long workplaceId;
  private String name;
  private String semsNumber;
  private Grade grade;
  private Double height;
  private Double horizontalLength;
  private Double verticalLength;
  private Shape shape;
  private Orientation orientation;
  private Double standardOxygen;
  private String remark;
  
  @Schema(description = "생성날짜") private LocalDate createdAt;
  @Schema(description = "수정날짜") private LocalDate modifiedAt;
}