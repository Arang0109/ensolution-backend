package com.project.easywork.pollutant.domain.dto;

import com.project.easywork.pollutant.domain.Phase;
import jakarta.validation.constraints.Min;
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
  @Min(value = 0, message = "0 이상의 값을 입력해주세요.")
  private Double samplingTime;
  private String samplingVolume;
}
