
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
@Table(name = "facility")
public class Facility {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "facility_id")
  private Long facilityId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prevention_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Prevention prevention;
  
  @Column(name = "facility_name", nullable = false, length = 100)
  private String facilityName;
  
  @Column(name = "fuel_usage", length = 10)
  private String fuelUsage;
  
  @Column(name = "item_output", length = 10)
  private String itemOutput;
  
  @Column(name = "fuel_input", length = 10)
  private String fuelInput;
  
  @Column(name = "fuel_type", length = 20)
  private String fuelType;
  
  @Column(columnDefinition = "LONGTEXT")
  private String remark;
}