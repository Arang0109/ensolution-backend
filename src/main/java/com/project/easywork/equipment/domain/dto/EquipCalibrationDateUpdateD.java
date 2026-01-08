package com.project.easywork.equipment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EquipCalibrationDateUpdateD {
  private LocalDate calibrationDate;
}
