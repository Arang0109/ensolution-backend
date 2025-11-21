package com.project.easywork.client.dto.update;

import com.project.easywork.common.constant.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WorkplaceUpdateDto {
  @Schema(description = "사업장 ID (PK)", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
  private Long workplaceId;
  private String workplaceName;
  private String address;
  private String bizNumber;
  private String businessCategory;
  private Size workplaceSize;
  private String remark;
}
