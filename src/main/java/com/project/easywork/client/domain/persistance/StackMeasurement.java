package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(
    name = "stack_measurement",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_stack_pollutant",
            columnNames = {"stack_id", "pollutant_id"}
        )
    }
)
public class StackMeasurement extends BaseEntity {
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stack_id")
  private Stack stack;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pollutant_id")
  private Pollutant pollutant;
  
  @Enumerated(EnumType.STRING)
  @Column()
  private Cycle cycle;
  
  @Column(precision = 10, scale = 1)
  private BigDecimal allowance;
  
  public void attachStack(Stack stack) {
    this.stack = stack;
    stack.getStackMeasurements().add(this);
  }
  
  public void attachPollutant(Pollutant pollutant) {
    this.pollutant = pollutant;
    pollutant.getStackMeasurements().add(this);
  }
  
  public void update(StackMeasurementUpdateD dto) {
    if (dto.getCycle() != null) {
      this.cycle = dto.getCycle();
    }
    if (dto.getAllowance() != null) {
      this.allowance = dto.getAllowance();
    }
  }
}