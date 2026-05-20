package com.project.easywork.measurement.mapper.draft_source_mapper;

import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.measurement.domain.dto.draft_source.items.MeasurementItemSource;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MeasurementItemSourceMapper {
  
  public MeasurementItemSource toSource(
      StackMeasurement stackMeasurement
  ) {
    if (stackMeasurement == null) return null;
    
    Pollutant pollutant = stackMeasurement.getPollutant();
    
    if (pollutant == null) {
      throw new IllegalStateException(
          "StackMeasurement[" + stackMeasurement.getId() + "] has no pollutant"
      );
    }
    
    return new MeasurementItemSource(
        stackMeasurement.getId(),
        pollutant.getId(),
        pollutant.getNameKr(),
        pollutant.getNameEn(),
        pollutant.getMethod(),
        pollutant.getEquipmentName(),
        pollutant.getTestMethodName(),
        pollutant.getSamplingTime(),
        pollutant.getSamplingVolume(),
        stackMeasurement.getCycle(),
        stackMeasurement.getAllowance()
    );
  }
  
  public List<MeasurementItemSource> toSources(
      List<StackMeasurement> measurements
  ) {
    
    if (measurements == null) return List.of();
    
    return measurements.stream()
        .map(this::toSource)
        .filter(Objects::nonNull)
        .toList();
  }
}
