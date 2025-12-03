package com.project.easywork.agency.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class VehicleResponseDto {
  @Schema(
      description = "차량 기본키",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  @Schema(description = "팀 ID (FK)", example = "1")
  private Long teamId;
  
  @Schema(description = "차량번호", example = "000도 0000")
  @NotBlank(message = "필수 입력")
  private String vehicleNumber;
}