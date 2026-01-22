package com.project.easywork.equipment.domain.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("equipment_calibrations")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentCalibrationDoc {
  @Id
  private String id;
  
  private String equipmentId;
  
  private LocalDate calibrationDate;
}
