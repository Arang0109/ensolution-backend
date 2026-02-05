package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientDoc {
  
  private CompanyDoc company;
  private StackDoc stack;
  
  // ------------------------------------
  // 의뢰기관 정보
  // ------------------------------------
  @Getter
  @Builder(toBuilder = true)
  public static class CompanyDoc {
    private Long companyId;
    private Long workplaceId;
    private String companyName;
    private String workplaceName;
    private String ceoName;
    private String address;
    private String bizNumber;
    private String manager;
    private String businessCategory;
    private Grade grade;
  }
  
  // ------------------------------------
  // 굴뚝 정보
  // ------------------------------------
  @Getter
  @Builder(toBuilder = true)
  public static class StackDoc {
    private Long stackId;
    private String name;
    private String semsNumber;
    private Grade grade;
    private BigDecimal height;
    private BigDecimal horizontalLength;
    private BigDecimal verticalLength;
    private Shape shape;
    private Orientation orientation;
    private BigDecimal standardOxygen;
    private List<PreventionDoc> preventions;
  }
  
  // ------------------------------------
  // 방지시설 정보
  // ------------------------------------
  @Getter
  @Builder(toBuilder = true)
  public static class PreventionDoc {
    private Long preventionId;
    private String name;
    private List<FacilityDoc> facilities;
    private List<TargetDoc> targets;
  }
  
  // ------------------------------------
  // 방지시설 하위 배출시설 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class FacilityDoc {
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
  @Builder(toBuilder = true)
  public static class TargetDoc {
    private Long targetId;
    private String targetSubstance;
    private BigDecimal removalEfficiency;
  }
}
