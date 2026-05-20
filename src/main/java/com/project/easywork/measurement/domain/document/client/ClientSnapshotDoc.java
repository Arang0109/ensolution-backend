package com.project.easywork.measurement.domain.document.client;

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
public class ClientSnapshotDoc {
  
  private CompanySnapshotDoc company;
  private StackSnapshotDoc stack;
  
  public ClientSnapshotDoc merge(ClientSnapshotDoc patch) {
    return this.toBuilder()
        .company(mergeCompany(patch.getCompany()))
        .stack(mergeStack(patch.getStack()))
        .build();
  }
  
  private CompanySnapshotDoc mergeCompany(CompanySnapshotDoc patchCompany) {
    if (patchCompany == null) return this.company;
    if (this.company == null) return patchCompany;
    return this.company.merge(patchCompany);
  }
  
  private StackSnapshotDoc mergeStack(StackSnapshotDoc patchStack) {
    if (patchStack == null) return this.stack;
    if (this.stack == null) return patchStack;
    return this.stack.merge(patchStack);
  }
  
  // ------------------------------------
  // 의뢰기관 정보
  // ------------------------------------
  @Getter
  @Builder(toBuilder = true)
  public static class CompanySnapshotDoc {
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
    
    public CompanySnapshotDoc merge(CompanySnapshotDoc patch) {
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
  public static class StackSnapshotDoc {
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
    private List<PreventionSnapshotDoc> preventions;
    
    public StackSnapshotDoc merge(StackSnapshotDoc patch) {
      
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
  public static class PreventionSnapshotDoc {
    private Long preventionId;
    private String name;
    private List<FacilitySnapshotDoc> facilities;
    private List<TargetSnapshotDoc> targets;
  }
  
  // ------------------------------------
  // 방지시설 하위 배출시설 정보
  // ------------------------------------
  @Getter
  @Builder
  public static class FacilitySnapshotDoc {
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
  public static class TargetSnapshotDoc {
    private Long targetId;
    private String targetSubstance;
    private BigDecimal removalEfficiency;
  }
}
