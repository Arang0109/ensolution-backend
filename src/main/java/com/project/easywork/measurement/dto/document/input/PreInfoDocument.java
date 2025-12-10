package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PreInfoDocument {
  
  private PreInfoCompanyDocument company;
  private PreInfoWorkplaceDocument workplace;
  private PreInfoStackDocument stack;
  private List<PreInfoPreventionDocument> preventions;
  
  // ------------------------------------
  // 회사 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class PreInfoCompanyDocument {
    private Long id;
    private String name;
    private String ceoName;
    private String bizNumber;
  }
  
  // ------------------------------------
  // 사업장 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class PreInfoWorkplaceDocument {
    private Long id;
    private String name;
    private String address;
    private String bizNumber;
    private String businessCategory;
    private Grade grade;
  }
  
  // ------------------------------------
  // 굴뚝 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class PreInfoStackDocument {
    private Long id;
    private String name;
    private String semsNumber;
    private Grade grade;
    private Double height;
    private Double horizontalLength;
    private Double verticalLength;
    private Shape shape;
    private Orientation orientation;
    private Double standardOxygen;
  }
  
  // ------------------------------------
  // 방지시설 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class PreInfoPreventionDocument {
    private String name;
    private List<PreInfoFacilityDocument> facilities;
    private List<PreInfoTargetDocument> targets;
  }
  
  // ------------------------------------
  // 방지시설 하위 배출시설 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class PreInfoFacilityDocument {
    private String name;
    private String fuelUsage;
    private String itemOutput;
    private String fuelInput;
    private String fuelType;
  }
  
  // ------------------------------------
  // 대상 오염물질 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class PreInfoTargetDocument {
    private String targetSubstance;
    private Double removalEfficiency;
  }
}
