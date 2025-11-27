package com.project.easywork.client.domain.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@ToString
@Table(name = "manager")
public class Manager {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "manager_id")
  private Long managerId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workplace_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Workplace workplace;
  
  @Column(name = "manager_name", nullable = false, length = 100)
  private String managerName;
  
  @Column(length = 100)
  private String email;
  
  @Column(name = "tel_number", length = 20)
  private String telNumber;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
}