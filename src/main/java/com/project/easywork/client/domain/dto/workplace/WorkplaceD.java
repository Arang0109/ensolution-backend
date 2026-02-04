package com.project.easywork.client.domain.dto.workplace;

import com.project.easywork.client.domain.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
public class WorkplaceD {
  @Schema(
      description = "사업장 ID (PK)",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  private String companyId;
  private String name;
  private String address;
  private String bizNumber;
  private String manager;
  private String businessCategory;
  private Grade grade;
  private String remark;
  
  @Schema(description = "생성날짜") private LocalDate createdAt;
  @Schema(description = "수정날짜") private LocalDate modifiedAt;
}