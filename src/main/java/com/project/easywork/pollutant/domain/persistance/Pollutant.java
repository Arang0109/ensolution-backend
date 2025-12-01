package com.project.easywork.pollutant.domain.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
  
  @Column(name = "sampling_time")
  private Double samplingTime;
  
  @Column(name = "sampling_volume", length = 10)
  private String samplingVolume;
}
