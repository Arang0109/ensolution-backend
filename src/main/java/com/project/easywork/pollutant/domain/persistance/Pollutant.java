package com.project.easywork.pollutant.domain.persistance;

import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.pollutant.domain.Phase;
import com.project.easywork.pollutant.domain.dto.PollutantUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
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
}
