package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.company.CompanyUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "company")
public class Company extends BaseEntity {
  @Column(nullable = false, length = 100)
  private String name;
  
  @Column
  private String address;
  
  @Column(name = "ceo_name", length = 100)
  private String ceoName;
  
  @Column(name = "biz_number", nullable = false, length = 10)
  private String bizNumber;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
  
  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Workplace> workplaces = new ArrayList<>();
  
  public void update(CompanyUpdateD dto) {
    
    if (dto.getName() != null && !dto.getName().isBlank()) {
      this.name = dto.getName();
    }
    
    if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
      this.address = dto.getAddress();
    }
    
    if (dto.getCeoName() != null && !dto.getCeoName().isBlank()) {
      this.ceoName = dto.getCeoName();
    }
    
    if (dto.getBizNumber() != null && !dto.getBizNumber().isBlank()) {
      this.bizNumber = dto.getBizNumber();
    }
    
    if (dto.getRemark() != null) {
      this.remark = dto.getRemark();
    }
  }
}
