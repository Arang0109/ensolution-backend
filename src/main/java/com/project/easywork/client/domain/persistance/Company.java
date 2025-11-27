package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.company.CompanyUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
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
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  @UpdateTimestamp
  @Column(name = "modified_at", nullable = false)
  private LocalDate modifiedAt;
  
  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private List<Workplace> workplaces = new ArrayList<>();
  
  public void update(CompanyUpdateRequestDto dto) {
    Optional.ofNullable(dto.getName())
        .filter(name -> !name.isBlank())
        .ifPresent(this::setName);
    
    Optional.ofNullable(dto.getAddress())
        .ifPresent(this::setAddress);
    
    Optional.ofNullable(dto.getCeoName())
        .ifPresent(this::setCeoName);
    
    Optional.ofNullable(dto.getBizNumber())
        .filter(biz -> !biz.isBlank())
        .ifPresent(this::setBizNumber);
    
    Optional.ofNullable(dto.getRemark())
        .ifPresent(this::setRemark);
  }
}
