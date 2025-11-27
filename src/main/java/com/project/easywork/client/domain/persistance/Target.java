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
@Table(name = "target")
public class Target {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "target_id")
  private Long targetId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prevention_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Prevention prevention;
  
  @Column(name = "target_substance", length = 100)
  private String targetSubstance;
  
  @Column(name = "removal_efficiency")
  private Double removalEfficiency;
}
