package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.DraftUpdateCommandD;
import com.project.easywork.measurement.dto.command.MeasurementPointCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.*;
import com.project.easywork.measurement.mapper.*;
import com.project.easywork.measurement.util.MeasurementPointCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MeasurementDraftUpdater {
  
  private final StackInfoMapper stackInfoMapper;
  private final WeatherMapper weatherMapper;
  private final MoistureMapper moistureMapper;
  private final ExhaustGasMapper exhaustGasMapper;
  private final MeasurementPointMapper measurementPointMapper;
  
  private final MeasurementPointCalculator measurementPointCalculator;
  
  public MeasurementDoc updateDraft(
      MeasurementDoc doc,
      DraftUpdateCommandD request
  ) {
    if (!doc.isDraft()) {
      throw new IllegalStateException("Draft 상태만 수정 가능");
    }
    
    MeasurementDoc updated = doc;
    
    if (request.stackInfo() != null) {
      updated = updated.toBuilder()
          .measurementPointCnt(
              measurementPointCalculator.calculate(
                  request.stackInfo().shape(),
                  request.stackInfo().horizontalLength(),
                  request.stackInfo().verticalLength()
              )
          )
          .build();
      
      ClientDoc.StackDoc newDoc = stackInfoMapper
          .toDocument(request.stackInfo())
          .normalize();
      
      ClientDoc.StackDoc merged =
          updated.getClient().getStack() == null
              ? newDoc
              : updated.getClient().getStack().merge(newDoc);
      
      updated = updated.updateStackInfo(merged);
    }
    
    if (request.weather() != null) {
      WeatherDoc newDoc = weatherMapper
          .toDocument(request.weather())
          .normalize();
      
      WeatherDoc merged =
          updated.getWeather() == null
              ? newDoc
              : updated.getWeather().merge(newDoc);
      
      updated = updated.updateWeather(merged);
    }
    
    if (request.moisture() != null) {
      MoistureDoc newDoc = moistureMapper
          .toDocument(request.moisture())
          .normalize();
      
      MoistureDoc merged =
          updated.getMoisture() == null
              ? newDoc
              : updated.getMoisture().merge(newDoc);
      
      updated = updated.updateMoisture(merged);
    }
    
    if (request.exhaustGas() != null) {
      ExhaustGasDoc newDoc = exhaustGasMapper
          .toDocument(request.exhaustGas())
          .normalize();
      
      ExhaustGasDoc merged =
          updated.getExhaustGas() == null
              ? newDoc
              : updated.getExhaustGas().merge(newDoc);
      
      updated = updated.updateExhaustGas(merged);
    }
    
    if (request.measurementPointInfo() != null) {
      List<MeasurementPointDoc> mergedPoints = new ArrayList<>();
      
      List<MeasurementPointCommandD> commands = request.measurementPointInfo();
      List<MeasurementPointDoc> currentPoints = updated.getMeasurementPoints();
      
      for (int i = 0; i < commands.size(); i++) {
        MeasurementPointCommandD command = commands.get(i);
        
        MeasurementPointDoc newDoc = measurementPointMapper
            .toDocument(command);
        
        MeasurementPointDoc merged =
            (currentPoints == null || currentPoints.size() <= i || currentPoints.get(i) == null)
                ? newDoc
                : currentPoints.get(i).merge(newDoc);
        
        mergedPoints.add(merged);
      }
      
      updated = updated.toBuilder()
          .measurementPoints(mergedPoints)
          .build();
    }
    
    
    return updated;
  }
}