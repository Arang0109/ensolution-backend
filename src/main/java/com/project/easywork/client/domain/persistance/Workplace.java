package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "workplace")
public class Workplace {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
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
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  @UpdateTimestamp
  @Column(name = "modified_at", nullable = false)
  private LocalDate modifiedAt;
  
  @OneToMany(mappedBy = "workplace", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Stack> stacks = new ArrayList<>();
  
  @OneToMany(mappedBy = "workplace", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Manager> managers = new ArrayList<>();
  
  public void update(WorkplaceUpdateRequestDto dto) {
    Optional.ofNullable(dto.getName())
        .filter(name -> !name.isBlank())
        .ifPresent(this::setName);
    
    Optional.ofNullable(dto.getBizNumber())
        .filter(biz -> !biz.isBlank())
        .ifPresent(this::setBizNumber);
    
    Optional.ofNullable(dto.getAddress())
        .ifPresent(this::setAddress);
    
    Optional.ofNullable(dto.getBusinessCategory())
        .ifPresent(this::setBusinessCategory);
    
    Optional.ofNullable(dto.getGrade())
        .ifPresent(this::setGrade);
    
    Optional.ofNullable(dto.getRemark())
        .ifPresent(this::setRemark);
  }
}