package com.project.easywork.pollutant.domain.persistance;

import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.pollutant.domain.Phase;
import com.project.easywork.pollutant.domain.dto.PollutantUpdateD;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
@Table(name = "pollutant")
public class Pollutant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true)
  private Long id;
  
  @Column(name = "name_kr", length = 100)
  private String nameKr;
  
  @Column(name = "name_en", length = 100)
  private String nameEn;
  
  @Column
  private String method;
  
  @Column
  @Enumerated(EnumType.STRING)
  private Phase phase;
  
  @Column(name = "equipment_name", length = 100)
  private String equipmentName;
  
  @Column(name = "test_method_name", length = 100)
  private String testMethodName;
  
  @Column(name = "sampling_time")
  private Double samplingTime;
  
  @Column(name = "sampling_volume", length = 10)
  private String samplingVolume;
  
  @OneToMany(mappedBy = "pollutant", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<StackMeasurement> stackMeasurements = new ArrayList<>();
  
  public void update(PollutantUpdateD dto) {
    PollutantBuilder builder = this.toBuilder();
    
    Optional.ofNullable(dto.getNameKr())
        .ifPresent(v -> this.nameKr = v);
    
    Optional.ofNullable(dto.getNameEn())
        .ifPresent(v -> this.nameEn = v);
    
    Optional.ofNullable(dto.getMethod())
        .ifPresent(v -> this.method = v);
    
    Optional.ofNullable(dto.getPhase())
        .ifPresent(v -> this.phase = v);
    
    Optional.ofNullable(dto.getEquipmentName())
        .ifPresent(v -> this.equipmentName = v);
    
    Optional.ofNullable(dto.getTestMethodName())
        .ifPresent(v -> this.testMethodName = v);
    
    Optional.ofNullable(dto.getSamplingTime())
        .ifPresent(v -> this.samplingTime = v);
    
    Optional.ofNullable(dto.getSamplingVolume())
        .ifPresent(v -> this.samplingVolume = v);
    
    apply(builder.build());
  }
  
  private void apply(Pollutant pollutant) {
    Optional.ofNullable(nameKr).ifPresent(v -> pollutant.nameKr = v);
    Optional.ofNullable(nameEn).ifPresent(v -> pollutant.nameEn = v);
    Optional.ofNullable(method).ifPresent(v -> pollutant.method = v);
    Optional.ofNullable(phase).ifPresent(v -> pollutant.phase = v);
    Optional.ofNullable(equipmentName).ifPresent(v -> pollutant.equipmentName = v);
    Optional.ofNullable(testMethodName).ifPresent(v -> pollutant.testMethodName = v);
    Optional.ofNullable(samplingTime).ifPresent(v -> pollutant.samplingTime = v);
    Optional.ofNullable(samplingVolume).ifPresent(v -> pollutant.samplingVolume = v);
  }
}
