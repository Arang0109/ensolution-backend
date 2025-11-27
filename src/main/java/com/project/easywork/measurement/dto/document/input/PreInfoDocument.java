package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.common.constant.Grade;
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
    private Long companyId;
    private String companyName;
    private String ceoName;
  }
  
  // ------------------------------------
  // 사업장 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class PreInfoWorkplaceDocument {
    private Long workplaceId;
    private String workplaceName;
    private String address;
    private String bizNumber;
    private String businessCategory;
    private Grade workplaceGrade;
  }
  
  // ------------------------------------
  // 굴뚝 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class PreInfoStackDocument {
    private Long stackId;
    private String stackName;
    private String semsNumber;
    private Grade stackGrade;
    private Double stackHeight;
    private String stackType;
    private String stackShape;
    private Double horizontalLength;
    private Double verticalLength;
  }
  
  // ------------------------------------
  // 방지시설 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class PreInfoPreventionDocument {
    private String preventionName;
    private List<PreInfoFacilityDocument> facilities;
    private List<PreInfoTargetDocument> targets;
  }
  
  // ------------------------------------
  // 방지시설 하위 배출시설 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class PreInfoFacilityDocument {
    private String facilityName;
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
