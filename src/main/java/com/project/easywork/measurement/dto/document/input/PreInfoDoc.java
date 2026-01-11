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
public class PreInfoDoc {
  private LocalDate measureDate;
  private String measurementType;
  private String teamName;
  private String vehicleNumber;
  private List<String> engineers;
  
  private List<StackMeasurementDoc> measurementItems;
  
  public void addMeasurementItems(List<StackMeasurementDoc> items) {
    if (this.measurementItems == null) {
      this.measurementItems = new ArrayList<>();
    }
    this.measurementItems.addAll(items);
  }
  
  public void merge(PreInfoDoc doc) {
    if (doc.measureDate != null) this.measureDate = doc.measureDate;
    if (doc.measurementType != null) this.measurementType = doc.measurementType;
    if (doc.teamName != null) this.teamName = doc.teamName;
    if (doc.vehicleNumber != null) this.vehicleNumber = doc.vehicleNumber;
    if (doc.engineers != null) this.engineers = doc.engineers;
  }
  
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
    private Cycle cycle;
    private BigDecimal allowance;
  }
}
