package com.project.easywork.equipment.domain.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "equipment")
public class Equipment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  // 관리번호
  @Column(name = "management_number", nullable = false, length = 100)
  private String managementNumber;
  
  // 시리얼 번호
  @Column(name = "serial_number", length = 100)
  private String serialNumber;
  
  // 모델명
  @Column(name = "model_name", nullable = false, length = 100)
  private String modelName;
  
  // 장비명(커스텀명)
  @Column(name = "equipment_name", length = 100)
  private String equipmentName;
  
  // 가격
  @Column(name = "price", precision = 12, scale = 2)
  private BigDecimal price;
  
  // 제조사
  @Column(name = "manufacturer", length = 100)
  private String manufacturer;
  
  // 제조 국가 / 원산지
  @Column(name = "origin_country", length = 100)
  private String originCountry;
  
  // 구입일
  @Column(name = "purchase_date")
  private LocalDate purchaseDate;
  
  // 사용 가능 여부 (true/false)
  @Column(name = "is_available")
  private Boolean isAvailable = true;
  
  // 교정 날짜
  @Column(name = "calibration_date", nullable = false)
  private LocalDate calibrationDate;
  
  // 교정 주기 (월 단위 예시)
  @Column(name = "calibration_cycle")
  private Integer calibrationCycle;
  
  // 비고
  @Lob
  private String remark;
  
  public void toggleAvailable() {
    this.isAvailable = !this.isAvailable;
  }
  
  public void updateCalibrationDate(LocalDate calibrationDate) {
    this.calibrationDate = calibrationDate;
  }
}