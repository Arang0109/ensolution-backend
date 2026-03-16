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
  
  public ClientDoc merge(ClientDoc patch) {
    return this.toBuilder()
        .company(mergeCompany(patch.getCompany()))
        .stack(mergeStack(patch.getStack()))
        .build();
  }
  
  private CompanyDoc mergeCompany(CompanyDoc patchCompany) {
    if (patchCompany == null) return this.company;
    if (this.company == null) return patchCompany;
    return this.company.merge(patchCompany);
  }
  
  private StackDoc mergeStack(StackDoc patchStack) {
    if (patchStack == null) return this.stack;
    if (this.stack == null) return patchStack;
    return this.stack.merge(patchStack);
  }
  
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
    
    public CompanyDoc merge(CompanyDoc patch) {
      return this.toBuilder()
          .companyName(patch.getCompanyName() != null ? patch.getCompanyName() : this.companyName)
          .workplaceName(patch.getWorkplaceName() != null ? patch.getWorkplaceName() : this.workplaceName)
          .ceoName(patch.getCeoName() != null ? patch.getCeoName() : this.ceoName)
          .address(patch.getAddress() != null ? patch.getAddress() : this.address)
          .bizNumber(patch.getBizNumber() != null ? patch.getBizNumber() : this.bizNumber)
          .manager(patch.getManager() != null ? patch.getManager() : this.manager)
          .businessCategory(patch.getBusinessCategory() != null ? patch.getBusinessCategory() : this.businessCategory)
          .grade(patch.getGrade() != null ? patch.getGrade() : this.grade)
          .build();
    }
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
    
    public StackDoc merge(StackDoc patch) {
      
      return this.toBuilder()
          .name(patch.getName() != null ? patch.getName() : this.name)
          .semsNumber(patch.getSemsNumber() != null ? patch.getSemsNumber() : this.semsNumber)
          .grade(patch.getGrade() != null ? patch.getGrade() : this.grade)
          .height(patch.getHeight() != null ? patch.getHeight() : this.height)
          .horizontalLength(patch.getHorizontalLength() != null ? patch.getHorizontalLength() : this.horizontalLength)
          .verticalLength(patch.getVerticalLength() != null ? patch.getVerticalLength() : this.verticalLength)
          .shape(patch.getShape() != null ? patch.getShape() : this.shape)
          .orientation(patch.getOrientation() != null ? patch.getOrientation() : this.orientation)
          .standardOxygen(patch.getStandardOxygen() != null ? patch.getStandardOxygen() : this.standardOxygen)
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
