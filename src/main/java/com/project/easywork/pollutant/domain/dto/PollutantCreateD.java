package com.project.easywork.pollutant.domain.dto;

import com.project.easywork.pollutant.domain.Method;
import com.project.easywork.pollutant.domain.Phase;
import jakarta.validation.constraints.Min;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PollutantCreateD {
  private String nameKr;
  private String nameEn;
  private Method method;
  private Phase phase;
  private String equipmentName;
  private String testMethodName;
  @Min(value = 0, message = "0 이상의 값을 입력해주세요.")
  private Double samplingTime;
  private String samplingVolume;
}
