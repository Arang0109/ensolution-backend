package com.project.easywork.agency.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TeamCreateRequestDto {
  @NotBlank(message = "필수 입력")  private String name;
}