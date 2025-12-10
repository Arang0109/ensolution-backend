package com.project.easywork.measurement.dto.command;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "측정 전 사전 정보 입력 DTO")
public record PreInfoCommandDto(
    
    @Schema(description = "의뢰업체 정보")
    PreInfoCompany company,
    
    @Schema(description = "사업장 정보")
    PreInfoWorkplace workplace,
    
    @Schema(description = "측정시설 정보")
    PreInfoStack stack,
    
    @Schema(description = "방지시설 목록")
    List<PreInfoPrevention> preventions
    
    /* stackMeasurements, team 등 추가 예정 */

) {
  
  // ------------------------
  // 회사 정보
  // ------------------------
  @Schema(description = "의뢰업체 정보")
  public record PreInfoCompany(
      
      @Schema(description = "의뢰업체 ID", example = "123")
      Long id,
      
      @Schema(description = "의뢰업체명", example = "㈜오션환경")
      String name,
      
      @Schema(description = "대표자명", example = "홍길동")
      String ceoName,
      
      @Schema(description = "사업자 번호", example = "123-45-67890")
      String bizNumber
  ) {}
  
  // ------------------------
  // 사업장 정보
  // ------------------------
  @Schema(description = "사업장 정보")
  public record PreInfoWorkplace(
      
      @Schema(description = "사업장 ID", example = "51")
      Long id,
      
      @Schema(description = "사업장명", example = "부산 공장 1")
      String name,
      
      @Schema(description = "주소", example = "부산시 사하구...")
      String address,
      
      @Schema(description = "사업자번호", example = "123-45-67890")
      String bizNumber,
      
      @Schema(description = "업종", example = "제조업")
      String businessCategory,
      
      @Schema(description = "사업장 규모", example = "TYPE_1")
      Grade grade
  ) {}
  
  // ------------------------
  // 굴뚝 정보
  // ------------------------
  @Schema(description = "측정시설 정보")
  public record PreInfoStack(
      
      @Schema(description = "측정시설 ID", example = "998")
      Long id,
      
      @Schema(description = "측정시설 이름", example = "1번 굴뚝")
      String name,
      
      @Schema(description = "SEMS 번호", example = "1111")
      String semsNumber,
      
      @Schema(description = "측정시설 규모", example = "TYPE_1")
      Grade grade,
      
      @Schema(description = "굴뚝 높이(m)", example = "20.5")
      Double height,
      
      @Schema(description = "수평 길이(m)", example = "1.0")
      Double horizontalLength,
      
      @Schema(description = "수직 길이(m)", example = "1.0")
      Double verticalLength,
      
      @Schema(description = "굴뚝 형상 (사각/원형)", example = "CIRCULAR")
      Shape shape,
      
      @Schema(description = "굴뚝 타입 (수직/수평)", example = "VERTICAL")
      Orientation orientation,
      
      @Schema(description = "표준산소농도 (%)", example = "21.0")
      Double standardOxygen
  ) {}
  
  // ------------------------
  // 방지시설 정보
  // ------------------------
  @Schema(description = "방지시설 정보")
  public record PreInfoPrevention(
      
      @Schema(description = "방지시설명", example = "세정집진시설")
      String name,
      
      @Schema(description = "시설 목록")
      List<PreInfoFacility> facilities,
      
      @Schema(description = "대상 오염물질 목록")
      List<PreInfoTarget> targets
  ) {}
  
  // ------------------------
  // 시설 정보
  // ------------------------
  @Schema(description = "방지시설 하위 시설 정보")
  public record PreInfoFacility(
      
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
  public record PreInfoTarget(
      
      @Schema(description = "오염물질명", example = "입자상")
      String targetSubstance,
      
      @Schema(description = "제거효율(%)", example = "99.0")
      Double removalEfficiency
  ) {}
}