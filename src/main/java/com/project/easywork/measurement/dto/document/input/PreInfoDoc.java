package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.plan.domain.MeasurementField;
import com.project.easywork.pollutant.domain.Method;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PreInfoDoc {
  private String referenceNumber;
  private LocalDate measureDate;
  private MeasurementField measurementField;
  private String measurementType;
  private Long teamId;
  private String teamName;
  private String vehicleNumber;
  private String mentor;
  private String mentee;
  
  private boolean simplifiedMeasurement;
  
  private List<StackMeasurementDoc> measurementItems;
  
  public PreInfoDoc merge(
      PreInfoDoc patch,
      List<StackMeasurementDoc> measurementPatch
  ) {
    return this.toBuilder()
        .referenceNumber(patch.getReferenceNumber() != null ? patch.getReferenceNumber() : this.referenceNumber)
        .measureDate(patch.getMeasureDate() != null ? patch.getMeasureDate() : this.measureDate)
        .measurementField(patch.getMeasurementField() != null ? patch.getMeasurementField() : this.measurementField)
        .measurementType(patch.getMeasurementType() != null ? patch.getMeasurementType() : this.measurementType)
        .teamId(patch.getTeamId() != null ? patch.getTeamId() : this.teamId)
        .teamName(patch.getTeamName() != null ? patch.getTeamName() : this.teamName)
        .vehicleNumber(patch.getVehicleNumber() != null ? patch.getVehicleNumber() : this.vehicleNumber)
        .mentor(patch.getMentor() != null ? patch.getMentor() : this.mentor)
        .mentee(patch.getMentee() != null ? patch.getMentee() : this.mentee)
        .measurementItems(
            measurementPatch != null ? measurementPatch : this.measurementItems
        )
        .build();
  }
  
  @Getter
  @Builder(toBuilder = true)
  @ToString
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
