package com.project.easywork.measurement.service;

import com.project.easywork.measurement.domain.Measurement;
import com.project.easywork.measurement.pipeline.MeasurementPipeline;
import com.project.easywork.measurement.dto.command.MeasurementCommandDto;
import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.mapper.*;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;
import com.project.easywork.measurement.pipeline.step.GasDensityCalculateStep;
import com.project.easywork.measurement.pipeline.step.MoistureCalculateStep;
import com.project.easywork.measurement.pipeline.step.PressureConvertStep;
import com.project.easywork.measurement.pipeline.step.ResultBuildStep;
import com.project.easywork.measurement.service_data.MeasurementDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MeasurementService {
  
  private final PreInfoMapper preInfoMapper;
  private final WeatherMapper weatherMapper;
  private final MoistureMapper moistureMapper;
  private final ExhaustGasMapper exhaustGasMapper;
  private final MeasurementDataService measurementDataService;
  
  public MeasurementDocument processAndSave(MeasurementCommandDto dto) {
    
    Measurement domain = Measurement.builder()
        .measurement(dto)
        .build();
    
    MeasurementContext context = new MeasurementContext(domain);
    
    MeasurementPipeline pipeline = new MeasurementPipeline()
        .addStep(new PressureConvertStep())
        .addStep(new MoistureCalculateStep())
        .addStep(new GasDensityCalculateStep())
        .addStep(new ResultBuildStep());
    pipeline.execute(context);
    
    return measurementDataService.save(MeasurementDocument.builder()
        .preInfo(preInfoMapper.toDocument(dto.preInfo()))
        .weather(weatherMapper.toDocument(dto.weather()))
        .moisture(moistureMapper.toDocument(dto.moisture()))
        .exhaustGas(exhaustGasMapper.toDocument(dto.exhaustGas()))
        .result(context.getResult())
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build());
  }
}
