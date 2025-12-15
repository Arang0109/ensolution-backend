package com.project.easywork.pollutant.domain.dto;

import com.project.easywork.pollutant.domain.Phase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PollutantCreateRequestDto {
  private String nameKr;
  private String nameEn;
  private String method;
  private Phase phase;
  private String equipmentName;
  private String testMethodName;
  private Double samplingTime;
  private String samplingVolume;
}
