package com.project.easywork.agency.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class TeamResponseDto {
  @Schema(
      description = "팀 기본키",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;
  
  @Schema(description = "팀 이름", example = "1팀")
  @NotBlank(message = "필수 입력")  private String name;
}