package com.project.easywork.measurement.service;

import com.project.easywork.common.constant.MeasurementStatus;
import com.project.easywork.measurement.domain.Measurement;
import com.project.easywork.measurement.dto.MeasurementDraftUpdateRequest;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementService {
  
  private final MeasurementDataService measurementDataService;
  
  private final PreInfoMapper preInfoMapper;
  private final WeatherMapper weatherMapper;
  private final MoistureMapper moistureMapper;
  private final ExhaustGasMapper exhaustGasMapper;
  
  public MeasurementDocument processAndSave(String objectId, MeasurementCommandDto dto) {
    
    MeasurementDocument doc = measurementDataService.findById(objectId);
    
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
    
    return measurementDataService.save(doc);
  }
  
  public MeasurementDocument createDraft(Long scheduleId) {
    MeasurementDocument doc = MeasurementDocument.builder()
        .scheduleId(scheduleId)
        .status(MeasurementStatus.DRAFT)
        .build();
    return measurementDataService.save(doc);
  }
  
  public void updateDraft(String objectId, MeasurementDraftUpdateRequest request) {
    MeasurementDocument document = measurementDataService.findById(objectId);
    
    // 상태 변경
    document.setStatus(MeasurementStatus.DRAFT);
    
    // 부분 업데이트
    if (request.getPreInfo() != null) document.setPreInfo(request.getPreInfo());
    if (request.getWeather() != null) document.setWeather(request.getWeather());
    if (request.getMoisture() != null) document.setMoisture(request.getMoisture());
    if (request.getExhaustGas() != null) document.setExhaustGas(request.getExhaustGas());
    if (request.getResult() != null) document.setResult(request.getResult());
    
    measurementDataService.save(document);
  }
}
