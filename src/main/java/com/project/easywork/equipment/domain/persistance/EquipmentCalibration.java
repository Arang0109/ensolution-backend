package com.project.easywork.equipment.domain.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "equipment_calibration")
public class EquipmentCalibration {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  // 어떤 장비의 교정 기록인지
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_id", nullable = false)
  @Setter
  private Equipment equipment;
  
  // 교정 날짜
  @Column(name = "calibration_date", nullable = false)
  private LocalDate calibrationDate;
  
  // 다음 교정 예정일
  @Column(name = "next_calibration_date")
  private LocalDate nextCalibrationDate;
  
  // 교정 증명서 파일 URL
  @Column(name = "certificate_url")
  private String certificateUrl;
  
  // 비고
  @Lob
  private String remark;
}
