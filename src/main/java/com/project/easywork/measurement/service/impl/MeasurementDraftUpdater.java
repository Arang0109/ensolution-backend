package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.MeasurementDraftUpdateCommandDto;
import com.project.easywork.measurement.dto.document.MeasurementDocument;
import com.project.easywork.measurement.dto.document.input.ExhaustGasDocument;
import com.project.easywork.measurement.dto.document.input.MoistureDocument;
import com.project.easywork.measurement.dto.document.input.PreInfoDocument;
import com.project.easywork.measurement.dto.document.input.WeatherDocument;
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
      PreInfoDocument newDoc = preInfoMapper.toDocument(request.preInfo());
      
      if (doc.getPreInfo() == null) {
        doc.updatePreInfo(newDoc);   // 최초 생성
      } else {
        doc.getPreInfo().merge(newDoc); // 부분 업데이트
      }
    }
    
    if (request.weather() != null) {
      WeatherDocument newDoc = weatherMapper
          .toDocument(request.weather())
          .normalize();
      
      if (doc.getWeather() == null) {
        doc.updateWeather(newDoc);
      } else {
        doc.getWeather().merge(newDoc);
      }
    }
    
    if (request.moisture() != null) {
      MoistureDocument newDoc = moistureMapper
          .toDocument(request.moisture())
          .normalize();
      
      if (doc.getMoisture() == null) {
        doc.updateMoisture(newDoc);
      } else {
        doc.getMoisture().merge(newDoc);
      }
    }
    
    if (request.exhaustGas() != null) {
      ExhaustGasDocument newDoc = exhaustGasMapper
          .toDocument(request.exhaustGas())
          .normalize();
      
      if (doc.getExhaustGas() == null) {
        doc.updateExhaustGas(newDoc);
      } else {
        doc.getExhaustGas().merge(newDoc);
      }
    }
  }
}