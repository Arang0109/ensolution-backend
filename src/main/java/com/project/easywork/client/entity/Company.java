package com.project.easywork.client.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "company_id")
  private Long companyId;
  
  @Column(name = "company_name", nullable = false, length = 100)
  private String companyName;
  
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
}
