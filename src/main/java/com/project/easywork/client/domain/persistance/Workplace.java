package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(
    name = "workplace",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_company_name",
            columnNames = {"company_id", "name"}
        )
    }
)
public class Workplace extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  @ToString.Exclude
  private Company company;
  
  @Column(nullable = false, length = 100)
  private String name;
  
  @Column
  private String address;
  
  @Column(name = "biz_number", nullable = false, length = 10)
  private String bizNumber;
  
  @Column(name = "manager")
  private String manager;
  
  @Column(name = "business_category", length = 100)
  private String businessCategory;
  
  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private Grade grade;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
  
  @OneToMany(mappedBy = "workplace", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Stack> stacks = new ArrayList<>();
  
  public void attachCompany(Company company) {
    this.company = company;
    company.getWorkplaces().add(this);
  }
  
  public void update(WorkplaceUpdateD dto) {
    
    if (dto.getName() != null && !dto.getName().isBlank()) {
      this.name = dto.getName();
    }
    
    if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
      this.address = dto.getAddress();
    }
    
    if (dto.getBizNumber() != null && !dto.getBizNumber().isBlank()) {
      this.bizNumber = dto.getBizNumber();
    }
    
    if (dto.getManager() != null && !dto.getManager().isBlank()) {
      this.manager = dto.getManager();
    }
    
    if (dto.getBusinessCategory() != null && !dto.getBusinessCategory().isBlank()) {
      this.businessCategory = dto.getBusinessCategory();
    }
    
    if (dto.getGrade() != null) {
      this.grade = dto.getGrade();
    }
    
    if (dto.getRemark() != null) {
      this.remark = dto.getRemark();
    }
  }
}