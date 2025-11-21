package com.project.easywork.measurement.dto.document;

import com.project.easywork.measurement.dto.document.input.*;
import com.project.easywork.measurement.dto.document.result.MeasurementResultDocument;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("measurements")
@Getter
@Builder
public class MeasurementDocument {
  
  @Id
  private String id;
  
  private PreInfoDocument preInfo;
  private WeatherDocument weather;
  private MoistureDocument moisture;
  private ExhaustGasDocument exhaustGas;
  
  private MeasurementResultDocument result;
  
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
