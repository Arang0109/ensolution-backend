package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDocument {
  
  private CompanyDocument company;
  private StackDocument stack;
  private List<PreventionDocument> preventions;
  
  // ------------------------------------
  // 의뢰기관 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class CompanyDocument {
    private Long companyId;
    private String companyName;
    private Long workplaceId;
    private String workplaceName;
    private String bizNumber;
    private String ceoName;
    private String address;
    private String businessCategory;
    private Grade grade;
  }
  
  // ------------------------------------
  // 굴뚝 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class StackDocument {
    private Long stackId;
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
  public static class PreventionDocument {
    private Long preventionId;
    private String name;
    private List<FacilityDocument> facilities;
    private List<TargetDocument> targets;
  }
  
  // ------------------------------------
  // 방지시설 하위 배출시설 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class FacilityDocument {
    private Long facilityId;
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
  public static class TargetDocument {
    private Long targetId;
    private String targetSubstance;
    private Double removalEfficiency;
  }
}
