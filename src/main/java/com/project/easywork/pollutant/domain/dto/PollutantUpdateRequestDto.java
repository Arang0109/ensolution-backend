package com.project.easywork.pollutant.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PollutantUpdateRequestDto {
  private String nameKr;
  private String nameEn;
  private String method;
  private Double samplingTime;
  private String samplingVolume;
}
