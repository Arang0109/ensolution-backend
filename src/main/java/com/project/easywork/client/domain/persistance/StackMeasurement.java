package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementUpdateRequestDto;
import com.project.easywork.common.constant.Cycle;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name = "stack_measurement")
public class StackMeasurement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Stack stack;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pollutant_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ToString.Exclude
  private Pollutant pollutant;
  
  @Enumerated(EnumType.STRING)
  @Column()
  private Cycle cycle;
  
  @Column()
  private Double allowance;
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  @UpdateTimestamp
  @Column(name = "modified_at", nullable = false)
  private LocalDate modifiedAt;
  
  public void update(StackMeasurementUpdateRequestDto dto) {
    Optional.ofNullable(dto.getCycle())
        .ifPresent(this::setCycle);
    
    Optional.ofNullable(dto.getAllowance())
        .ifPresent(this::setAllowance);
  }
}
