package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.MeasurementDraftUpdateCommandDto;
import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeasurementDraftUpdater {
  
  private final PreInfoMapper preInfoMapper;
  private final ClientMapper clientMapper;
  private final WeatherMapper weatherMapper;
  private final MoistureMapper moistureMapper;
  private final ExhaustGasMapper exhaustGasMapper;
  
  public void updateDraft(
      MeasurementDocument doc,
      MeasurementDraftUpdateCommandDto request
  ) {
    if (!doc.isDraft()) {
      throw new IllegalStateException("Draft 상태만 수정 가능");
    }
    
    if (request.preInfo() != null) {
      doc.updatePreInfo(preInfoMapper.toDocument(request.preInfo()));
    }
    if (request.client() != null) {
      doc.updateClient(clientMapper.toDocument(request.client()));
    }
    if (request.weather() != null) {
      doc.updateWeather(weatherMapper.toDocument(request.weather()));
    }
    if (request.moisture() != null) {
      doc.updateMoisture(moistureMapper.toDocument(request.moisture()));
    }
    if (request.exhaustGas() != null) {
      doc.updateExhaustGas(exhaustGasMapper.toDocument(request.exhaustGas()));
    }
  }
}