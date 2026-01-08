package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
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
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Company company;
  
  @Column(nullable = false, length = 100)
  private String name;
  
  @Column
  private String address;
  
  @Column(name = "biz_number", nullable = false, length = 10)
  private String bizNumber;
  
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
    WorkplaceBuilder<?, ?> builder = this.toBuilder();
    
    Optional.ofNullable(dto.getName()).filter(name -> !name.isBlank()).ifPresent(builder::name);
    Optional.ofNullable(dto.getAddress()).filter(address -> !address.isBlank()).ifPresent(builder::address);
    Optional.ofNullable(dto.getBizNumber()).filter(bizNumber -> !bizNumber.isBlank()).ifPresent(builder::bizNumber);
    Optional.ofNullable(dto.getBusinessCategory()).filter(category -> !category.isBlank()).ifPresent(builder::businessCategory);
    Optional.ofNullable(dto.getGrade()).ifPresent(builder::grade);
    Optional.ofNullable(dto.getRemark()).ifPresent(builder::remark);
    
    apply(builder.build());
  }
  
  private void apply(Workplace workplace) {
    this.name = workplace.name;
    this.address = workplace.address;
    this.bizNumber = workplace.bizNumber;
    this.businessCategory = workplace.businessCategory;
    this.grade = workplace.grade;
    this.remark = workplace.remark;
  }
}