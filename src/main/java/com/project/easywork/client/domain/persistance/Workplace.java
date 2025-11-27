package com.project.easywork.client.domain.persistance;

import com.project.easywork.common.constant.Size;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "workplace")
public class Workplace {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "workplace_id")
  private Long workplaceId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Company company;
  
  @Column(name = "workplace_name", nullable = false, length = 100)
  private String workplaceName;
  
  @Column
  private String address;
  
  @Column(name = "biz_number", nullable = false, length = 10)
  private String bizNumber;
  
  @Column(name = "business_category", length = 100)
  private String businessCategory;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "workplace_size", length = 10)
  private Size workplaceSize;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
  
  @OneToMany(mappedBy = "workplace", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Stack> stacks = new ArrayList<>();
  
  @OneToMany(mappedBy = "workplace", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Manager> managers = new ArrayList<>();
  
  public void update(WorkplaceUpdateDto dto) {
    this.workplaceName = dto.getWorkplaceName();
    this.bizNumber = dto.getBizNumber();
    this.address = dto.getAddress();
    this.businessCategory = dto.getBusinessCategory();
    this.workplaceSize = dto.getWorkplaceSize();
    this.remark = dto.getRemark();
  }
}