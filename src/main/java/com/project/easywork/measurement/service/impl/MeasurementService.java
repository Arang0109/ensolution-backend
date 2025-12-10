package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.pipeline.domain.Measurement;
import com.project.easywork.measurement.dto.MeasurementDraftUpdateCommandDto;
import com.project.easywork.measurement.pipeline.MeasurementPipeline;
import com.project.easywork.measurement.dto.command.MeasurementCommandDto;
import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.mapper.*;
import com.project.easywork.measurement.pipeline.context.MeasurementContext;
import com.project.easywork.measurement.pipeline.step.GasDensityCalculateStep;
import com.project.easywork.measurement.pipeline.step.MoistureCalculateStep;
import com.project.easywork.measurement.pipeline.step.PressureConvertStep;
import com.project.easywork.measurement.pipeline.step.ResultBuildStep;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementService implements IMeasurementService {
  
  private final IMeasurementDataService measurementDataService;
  
  private final PreInfoMapper preInfoMapper;
  private final WeatherMapper weatherMapper;
  private final MoistureMapper moistureMapper;
  private final ExhaustGasMapper exhaustGasMapper;
  
  @Override
  public void createDraft(Long scheduleId) {
    MeasurementDocument emptyDoc = MeasurementDocument.builder()
        .scheduleId(scheduleId)
        .status(MeasurementStatus.DRAFT)
        .build();
    
    measurementDataService.save(emptyDoc);
  }
  
  @Override
  public void updateDraft(Long scheduleId, MeasurementDraftUpdateCommandDto request) {
    MeasurementDocument document = measurementDataService.findByScheduleId(scheduleId);
    
    document.setStatus(MeasurementStatus.DRAFT); // 상태 변경
    
    document.setPreInfo(preInfoMapper.toDocument(request.preInfo()));
    document.setWeather(weatherMapper.toDocument(request.weather()));
    document.setMoisture(moistureMapper.toDocument(request.moisture()));
    document.setExhaustGas(exhaustGasMapper.toDocument(request.exhaustGas()));
    
    document.setResult(null);
    
    measurementDataService.save(document);
  }
  
  @Override
  public void deleteDraft(Long scheduleId) {
    measurementDataService.deleteByScheduleId(scheduleId);
  }
  
  @Override
  public void saveDocument(Long scheduleId, MeasurementCommandDto dto) {
    
    MeasurementDocument doc = measurementDataService.findByScheduleId(scheduleId);
    
    doc.setPreInfo(preInfoMapper.toDocument(dto.preInfo()));
    doc.setWeather(weatherMapper.toDocument(dto.weather()));
    doc.setMoisture(moistureMapper.toDocument(dto.moisture()));
    doc.setExhaustGas(exhaustGasMapper.toDocument(dto.exhaustGas()));
    
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
    
    doc.setResult(context.getResult());
    doc.setStatus(MeasurementStatus.COMPLETED);
    
    measurementDataService.save(doc);
  }
}
