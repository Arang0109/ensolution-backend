package com.project.easywork.client.domain.persistance;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
    name = "stack_measurement",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_stack_pollutant",
            columnNames = {"stack_id", "pollutant_id"}
        )
    }
)
public class StackMeasurement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
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
  
  @CreationTimestamp
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;
  
  @UpdateTimestamp
  @Column(name = "modified_at", nullable = false)
  private LocalDate modifiedAt;
  
  public void attachStack(Stack stack) {
    this.stack = stack;
  }
  
  public void attachPollutant(Pollutant pollutant) {
    this.pollutant = pollutant;
  }
}