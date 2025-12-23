
package com.project.easywork.client.domain.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "facility")
public class Facility {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prevention_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Prevention prevention;
  
  @Column(nullable = false, length = 100)
  private String name;
  
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
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  @UpdateTimestamp
  @Column(name = "modified_at", nullable = false)
  private LocalDate modifiedAt;
  
  public void attachPrevention(Prevention prevention) {
    this.prevention = prevention;
  }
}