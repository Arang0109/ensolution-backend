package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.client.domain.Cycle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreInfoDocument {
  private LocalDate measureDate;
  private String measurementType;
  private String teamName;
  private String vehicleNumber;
  private List<String> engineers;
  
  private List<StackMeasurementDocument> measurementItems;
  
  private String particularEquipmentName;
  private String pitotTubeName;
  
  @Getter
  @Builder
  public static class StackMeasurementDocument {
    private Long stackMeasurementId;
    private Long pollutantId;
    private String pollutantNameKr;
    private String pollutantNameEn;
    private Cycle cycle;
    private Double allowance;
  }
}
