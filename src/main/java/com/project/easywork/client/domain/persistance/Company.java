package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.company.CompanyUpdateD;
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
    CompanyBuilder<?, ?> builder = this.toBuilder();
    
    Optional.ofNullable(dto.getName()).filter(name -> !name.isBlank()).ifPresent(builder::name);
    Optional.ofNullable(dto.getAddress()).filter(address -> !address.isBlank()).ifPresent(builder::address);
    Optional.ofNullable(dto.getCeoName()).filter(ceoName -> !ceoName.isBlank()).ifPresent(builder::ceoName);
    Optional.ofNullable(dto.getBizNumber()).filter(bizNumber -> !bizNumber.isBlank()).ifPresent(builder::bizNumber);
    Optional.ofNullable(dto.getRemark()).ifPresent(builder::remark);
    
    apply(builder.build());
  }
  
  private void apply(Company company) {
    this.name = company.name;
    this.address = company.address;
    this.ceoName = company.ceoName;
    this.bizNumber = company.bizNumber;
    this.remark = company.remark;
  }
}
