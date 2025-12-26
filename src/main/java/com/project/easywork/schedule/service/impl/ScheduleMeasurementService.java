package com.project.easywork.schedule.service.impl;

import com.project.easywork.schedule.domain.dto.ScheduleMeasurementCreateReqDto;
import com.project.easywork.schedule.domain.dto.ScheduleMeasurementResDto;
import com.project.easywork.schedule.domain.persistance.Schedule;
import com.project.easywork.schedule.domain.persistance.ScheduleMeasurement;
import com.project.easywork.schedule.mapper.ScheduleMeasurementMapper;
import com.project.easywork.schedule.service.IScheduleMeasurementService;
import com.project.easywork.schedule.service_data.IScheduleMeasurementDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleMeasurementService implements IScheduleMeasurementService {
  
  private final IScheduleMeasurementDataService scheduleMeasurementDataService;
  private final ScheduleMeasurementMapper scheduleMeasurementMapper;
  
  @Override
  public ScheduleMeasurementResDto register(ScheduleMeasurementCreateReqDto dto) {
    ScheduleMeasurement scheduleMeasurement = scheduleMeasurementDataService.save(
        scheduleMeasurementMapper.toEntity(dto)
    );
    return scheduleMeasurementMapper.toDto(scheduleMeasurement);
  }
  
  public void registerAll(Long scheduleId, List<Long> measurementIds) {
    List<ScheduleMeasurementCreateReqDto> dtos =
        measurementIds.stream()
            .map(id -> ScheduleMeasurementCreateReqDto.builder()
                .scheduleId(scheduleId)
                .stackMeasurementId(id)
                .build())
            .toList();
    
    scheduleMeasurementDataService.saveAll(
        scheduleMeasurementMapper.toEntityList(dtos)
    );
  }
}