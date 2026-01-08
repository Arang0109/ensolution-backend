package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.client.domain.dto.stack_measurement.StackMeasurementUpdateD;
import com.project.easywork.common.domain.BaseEntity;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Optional;

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
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Stack stack;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pollutant_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Pollutant pollutant;
  
  @Enumerated(EnumType.STRING)
  @Column()
  private Cycle cycle;
  
  @Column()
  private Double allowance;
  
  public void attachStack(Stack stack) {
    this.stack = stack;
    stack.getStackMeasurements().add(this);
  }
  
  public void attachPollutant(Pollutant pollutant) {
    this.pollutant = pollutant;
    pollutant.getStackMeasurements().add(this);
  }
  
  public void update(StackMeasurementUpdateD dto) {
    StackMeasurementBuilder<?, ?> builder = this.toBuilder();
    
    Optional.ofNullable(dto.getCycle())
        .ifPresent(v -> this.cycle = v);
    
    Optional.ofNullable(dto.getAllowance())
        .ifPresent(v -> this.allowance = v);
    
    apply(builder.build());
  }
  
  private void apply(StackMeasurement stackMeasurement) {
    this.cycle = stackMeasurement.cycle;
    this.allowance = stackMeasurement.allowance;
  }
}