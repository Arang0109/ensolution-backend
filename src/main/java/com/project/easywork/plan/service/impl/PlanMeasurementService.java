package com.project.easywork.plan.service.impl;

import com.project.easywork.plan.domain.dto.MeasurementItemsCreateD;
import com.project.easywork.plan.domain.dto.PlanMeasurementsD;
import com.project.easywork.plan.domain.persistance.PlanMeasurement;
import com.project.easywork.plan.mapper.PlanMeasurementMapper;
import com.project.easywork.plan.service.IPlanMeasurementService;
import com.project.easywork.plan.service_data.IPlanMeasurementDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanMeasurementService implements IPlanMeasurementService {
  
  private final IPlanMeasurementDataService scheduleMeasurementDataService;
  private final PlanMeasurementMapper planMeasurementMapper;
  
  @Override
  public PlanMeasurementsD register(MeasurementItemsCreateD dto) {
    PlanMeasurement planMeasurement = scheduleMeasurementDataService.save(
        planMeasurementMapper.toEntity(dto)
    );
    return planMeasurementMapper.toDto(planMeasurement);
  }
  
  public void registerAll(Long planId, List<Long> measurementIds) {
    List<MeasurementItemsCreateD> dtos =
        measurementIds.stream()
            .map(id -> MeasurementItemsCreateD.builder()
                .planId(planId)
                .stackMeasurementId(id)
                .build())
            .toList();
    
    scheduleMeasurementDataService.saveAll(
        planMeasurementMapper.toEntityList(dtos)
    );
  }
}