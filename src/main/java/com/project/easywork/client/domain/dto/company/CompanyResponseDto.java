package com.project.easywork.client.domain.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
public class CompanyResponseDto {
  @Schema(
      description = "의뢰업체 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private String name;
  private String address;
  private String ceoName;
  private String bizNumber;
  private String remark;
  
  @Schema(description = "생성날짜") private LocalDate createdAt;
  @Schema(description = "수정날짜") private LocalDate modifiedAt;
}