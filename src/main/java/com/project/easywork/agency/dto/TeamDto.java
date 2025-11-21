package com.project.easywork.agency.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TeamDto {
  @Schema(
      description = "팀 기본키",
      accessMode = Schema.AccessMode.READ_ONLY)
  private Long teamId;
  
  @Schema(description = "팀 이름", example = "1팀")
  @NotBlank(message = "필수 입력")  private String teamName;
}