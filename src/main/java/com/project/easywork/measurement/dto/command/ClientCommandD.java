package com.project.easywork.measurement.dto.command;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "측정 전 사전 정보 입력 DTO")
public record ClientCommandD(
    
    @Schema(description = "의뢰기관 정보")
    Company company,
    
    @Schema(description = "측정시설 정보")
    Stack stack,
    
    @Schema(description = "방지시설 목록")
    List<Prevention> preventions
    
    /* stackMeasurements, team 등 추가 예정 */

) {
  
  // ------------------------
  // 의뢰기관 정보
  // ------------------------
  @Schema(description = "의뢰기관 정보")
  public record Company(
      
      Long companyId,
      
      @Schema(description = "의뢰업체명", example = "㈜오션환경")
      String companyName,
      
      Long workplaceId,
      
      @Schema(description = "사업장명", example = "부산 공장 1")
      String workplaceName,
      
      @Schema(description = "사업자 번호", example = "123-45-67890")
      String bizNumber,
      
      @Schema(description = "대표자명", example = "홍길동")
      String ceoName,
      
      @Schema(description = "주소", example = "부산시 사하구...")
      String address,
      
      @Schema(description = "업종", example = "제조업")
      String businessCategory,
      
      @Schema(description = "사업장 규모", example = "TYPE_1")
      Grade grade
  ) {}
  
  // ------------------------
  // 굴뚝 정보
  // ------------------------
  @Schema(description = "측정시설 정보")
  public record Stack(
      
      @Schema(description = "측정시설 ID", example = "998")
      Long stackId,
      
      @Schema(description = "측정시설 이름", example = "1번 굴뚝")
      String name,
      
      @Schema(description = "SEMS 번호", example = "1111")
      String semsNumber,
      
      @Schema(description = "측정시설 규모", example = "TYPE_1")
      Grade grade,
      
      @Schema(description = "굴뚝 높이(m)", example = "20.5")
      BigDecimal height,
      
      @Schema(description = "수평 길이(m)", example = "1.0")
      BigDecimal horizontalLength,
      
      @Schema(description = "수직 길이(m)", example = "1.0")
      BigDecimal verticalLength,
      
      @Schema(description = "굴뚝 형상 (사각/원형)", example = "CIRCULAR")
      Shape shape,
      
      @Schema(description = "굴뚝 타입 (수직/수평)", example = "VERTICAL")
      Orientation orientation,
      
      @Schema(description = "표준산소농도 (%)", example = "21.0")
      BigDecimal standardOxygen
  ) {}
  
  // ------------------------
  // 방지시설 정보
  // ------------------------
  @Schema(description = "방지시설 정보")
  public record Prevention(
      
      @Schema(description = "방지시설명", example = "세정집진시설")
      String name,
      
      @Schema(description = "시설 목록")
      List<Facility> facilities,
      
      @Schema(description = "대상 오염물질 목록")
      List<Target> targets
  ) {}
  
  // ------------------------
  // 시설 정보
  // ------------------------
  @Schema(description = "방지시설 하위 시설 정보")
  public record Facility(
      
      @Schema(description = "시설명", example = "데드너")
      String name,
      
      @Schema(description = "연료 사용량", example = "000")
      String fuelUsage,
      
      @Schema(description = "제품 생산량", example = "12대")
      String itemOutput,
      
      @Schema(description = "연료 투입량", example = "000")
      String fuelInput,
      
      @Schema(description = "연료 종류", example = "도료")
      String fuelType
  ) {}
  
  // ------------------------
  // 대상 오염물질 정보
  // ------------------------
  @Schema(description = "처리 대상 오염물질 정보")
  public record Target(
      
      @Schema(description = "오염물질명", example = "입자상")
      String targetSubstance,
      
      @Schema(description = "제거효율(%)", example = "99.0")
      BigDecimal removalEfficiency
  ) {}
}