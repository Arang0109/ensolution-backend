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
public class ClientDoc {
  
  private CompanyDoc company;
  private StackDoc stack;
  private List<PreventionDoc> preventions;
  
  public void merge(ClientDoc doc) {
    if (doc == null) return;
    
    if (doc.company != null) {
      this.company = doc.company;
    }
    
    if (doc.stack != null) {
      if (this.stack == null) {
        this.stack = doc.stack;
      } else {
        this.stack = this.stack.merge(doc.stack);
      }
    }
    
    if (doc.preventions != null) {
      this.preventions = doc.preventions;
    }
  }
  
  public ClientDoc normalize() {
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
  @Builder(toBuilder = true)
  public static class CompanyDoc {
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
    
    public StackDoc merge(StackDoc doc) {
      if (doc == null) return this;
      
      Shape mergedShape = doc.shape != null ? doc.shape : this.shape;
      
      BigDecimal mergedVerticalLength =
          mergedShape == Shape.CIRCULAR
              ? null
              : (doc.verticalLength != null ? doc.verticalLength : this.verticalLength);
      
      return this.toBuilder()
          .stackId(doc.stackId != null ? doc.stackId : this.stackId)
          .name(doc.name != null ? doc.name : this.name)
          .semsNumber(doc.semsNumber != null ? doc.semsNumber : this.semsNumber)
          .grade(doc.grade != null ? doc.grade : this.grade)
          .height(doc.height != null ? doc.height : this.height)
          .horizontalLength(doc.horizontalLength != null ? doc.horizontalLength : this.horizontalLength)
          .verticalLength(mergedVerticalLength)
          .shape(mergedShape)
          .orientation(doc.orientation != null ? doc.orientation : this.orientation)
          .standardOxygen(doc.standardOxygen != null ? doc.standardOxygen : this.standardOxygen)
          .build();
    }
    
    public StackDoc normalize() {
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
  public static class PreventionDoc {
    private Long preventionId;
    private String name;
    private List<FacilityDoc> facilities;
    private List<TargetDoc> targets;
    
    public PreventionDoc normalize() {
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
    
    public TargetDoc normalize() {
      return this.toBuilder()
          .targetId(targetId)
          .targetSubstance(targetSubstance)
          .removalEfficiency(scale(removalEfficiency, 1))
          .build();
    }
  }
}
