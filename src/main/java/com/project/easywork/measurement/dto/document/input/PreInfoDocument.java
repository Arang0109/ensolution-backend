package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.client.domain.Cycle;
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
public class PreInfoDocument {
  private LocalDate measureDate;
  private String measurementType;
  private String teamName;
  private String vehicleNumber;
  private List<String> engineers;
  
  private List<StackMeasurementDocument> measurementItems;
  
  private String particularEquipmentName;
  private String pitotTubeName;
  
  public void addMeasurementItems(List<StackMeasurementDocument> items) {
    if (this.measurementItems == null) {
      this.measurementItems = new ArrayList<>();
    }
    this.measurementItems.addAll(items);
  }
  
  public void replaceMeasurementItems(List<StackMeasurementDocument> items) {
    this.measurementItems = new ArrayList<>(items);
  }
  
  @Getter
  @Builder
  public static class StackMeasurementDocument {
    private Long stackMeasurementId;
    private Long pollutantId;
    private String pollutantNameKr;
    private String pollutantNameEn;
    private Cycle cycle;
    private BigDecimal allowance;
  }
}
