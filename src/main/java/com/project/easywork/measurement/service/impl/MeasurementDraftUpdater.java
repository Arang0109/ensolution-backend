package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.DraftUpdateCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.*;
import com.project.easywork.measurement.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeasurementDraftUpdater {
  
  private final PreInfoMapper preInfoMapper;
  private final EquipmentDocMapper equipmentDocMapper;
  private final WeatherMapper weatherMapper;
  private final MoistureMapper moistureMapper;
  private final ExhaustGasMapper exhaustGasMapper;
  
  public void updateDraft(
      MeasurementDoc doc,
      DraftUpdateCommandD request
  ) {
    if (!doc.isDraft()) {
      throw new IllegalStateException("Draft 상태만 수정 가능");
    }
    
    if (request.preInfo() != null) {
      PreInfoDoc newDoc = preInfoMapper.toDocument(request.preInfo());
      
      if (doc.getPreInfo() == null) {
        doc.updatePreInfo(newDoc);   // 최초 생성
      } else {
        doc.getPreInfo().merge(newDoc); // 부분 업데이트
      }
    }
    
    if (request.equipment() != null) {
      EquipmentDoc newDoc = equipmentDocMapper.toDocument(request.equipment());
      
      if (doc.getEquipment() == null) {
        doc.updateEquipment(newDoc);
      } else {
        doc.getEquipment().merge(newDoc);
      }
    }
    
    if (request.weather() != null) {
      WeatherDoc newDoc = weatherMapper
          .toDocument(request.weather())
          .normalize();
      
      if (doc.getWeather() == null) {
        doc.updateWeather(newDoc);
      } else {
        doc.getWeather().merge(newDoc);
      }
    }
    
    if (request.moisture() != null) {
      MoistureDoc newDoc = moistureMapper
          .toDocument(request.moisture())
          .normalize();
      
      if (doc.getMoisture() == null) {
        doc.updateMoisture(newDoc);
      } else {
        doc.getMoisture().merge(newDoc);
      }
    }
    
    if (request.exhaustGas() != null) {
      ExhaustGasDoc newDoc = exhaustGasMapper
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