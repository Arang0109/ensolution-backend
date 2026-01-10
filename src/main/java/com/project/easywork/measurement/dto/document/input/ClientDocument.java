package com.project.easywork.measurement.dto.document.input;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientDocument {
  
  private CompanyDocument company;
  private StackDocument stack;
  private List<PreventionDocument> preventions;
  
  public ClientDocument normalize() {
    return this.toBuilder()
        .company(company)
        .stack(stack != null ? stack.normalize() : null)
        .preventions(
            preventions == null ? null :
                preventions.stream()
                    .map(p -> p != null ? p.normalize() : null)
                    .toList()
        )
        .build();
  }
  
  private static BigDecimal scale(BigDecimal value, int scale) {
    return value == null ? null : value.setScale(scale, RoundingMode.HALF_UP);
  }
  
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
  @Builder(toBuilder = true)
  public static class StackDocument {
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
    
    public StackDocument normalize() {
      return this.toBuilder()
          .stackId(stackId)
          .name(name)
          .semsNumber(semsNumber)
          .grade(grade)
          .height(scale(height, 0))
          .horizontalLength(scale(horizontalLength, 3))
          .verticalLength(scale(verticalLength, 3))
          .shape(shape)
          .orientation(orientation)
          .standardOxygen(scale(standardOxygen, 1))
          .build();
    }
  }
  
  // ------------------------------------
  // 방지시설 정보
  // ------------------------------------
  @Getter
  @Builder(toBuilder = true)
  public static class PreventionDocument {
    private Long preventionId;
    private String name;
    private List<FacilityDocument> facilities;
    private List<TargetDocument> targets;
    
    public PreventionDocument normalize() {
      return this.toBuilder()
          .preventionId(preventionId)
          .name(name)
          .facilities(
              facilities == null ? null : facilities
          )
          .targets(
              targets == null ? null :
                  targets.stream()
                      .map(t -> t != null ? t.normalize() : null)
                      .toList()
          )
          .build();
    }
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
  @Builder(toBuilder = true)
  public static class TargetDocument {
    private Long targetId;
    private String targetSubstance;
    private BigDecimal removalEfficiency;
    
    public TargetDocument normalize() {
      return this.toBuilder()
          .targetId(targetId)
          .targetSubstance(targetSubstance)
          .removalEfficiency(scale(removalEfficiency, 1))
          .build();
    }
  }
}
