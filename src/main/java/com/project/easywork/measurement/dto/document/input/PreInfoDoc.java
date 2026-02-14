package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.pollutant.domain.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreInfoDoc {
  private LocalDate measureDate;
  private String measurementType;
  private Long teamId;
  private String teamName;
  private String vehicleNumber;
  private String mentor;
  private String mentee;
  
  private boolean simplifiedMeasurement;
  
  private List<StackMeasurementDoc> measurementItems;
  
  public void replaceMeasurementItems(List<StackMeasurementDoc> items) {
    this.measurementItems = new ArrayList<>(items);
  }
  
  @Getter
  @Builder
  public static class StackMeasurementDoc {
    private Long stackMeasurementId;
    private Long pollutantId;
    private String pollutantNameKr;
    private String pollutantNameEn;
    private Method method;
    private String equipmentName;
    private String testMethodName;
    private Double samplingTime;
    private String samplingVolume;
    private Cycle cycle;
    private BigDecimal allowance;
  }
}
