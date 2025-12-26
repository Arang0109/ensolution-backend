package com.project.easywork.schedule.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.mapper.CompanyMapper;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.client.service.IStackService;
import com.project.easywork.common.resolver.DomainEntityResolver;
import com.project.easywork.schedule.domain.ScheduleStatus;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.schedule.domain.dto.*;
import com.project.easywork.schedule.domain.persistance.Schedule;
import com.project.easywork.schedule.mapper.ScheduleMapper;
import com.project.easywork.schedule.mapper.ScheduleMeasurementMapper;
import com.project.easywork.schedule.service.IScheduleMeasurementService;
import com.project.easywork.schedule.service.IScheduleService;
import com.project.easywork.schedule.service_data.IScheduleDataService;
import com.project.easywork.schedule.service_data.IScheduleMeasurementDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService implements IScheduleService {
  
  private final IScheduleDataService scheduleDataService;
  private final IScheduleMeasurementService scheduleMeasurementService;
  
  private final IStackService stackService;
  private final IMeasurementService measurementService;
  
  private final ScheduleMapper scheduleMapper;
  private final ScheduleMeasurementMapper scheduleMeasurementMapper;
  private final CompanyMapper companyMapper;
  private final WorkplaceMapper workplaceMapper;
  
  private final DomainEntityResolver domainEntityResolver;
  
  @Override
  public ScheduleResDto register(ScheduleCreateReqDto scheduleCreateReqDto) {
    Stack stack = domainEntityResolver.getStackOrThrow(scheduleCreateReqDto.getStackId());
    Team team = domainEntityResolver.getTeamOrThrow(scheduleCreateReqDto.getTeamId());
    
    Schedule schedule = scheduleMapper.toEntity(scheduleCreateReqDto);
    schedule.attachStack(stack);
    schedule.attachTeam(team);
    
    schedule = scheduleDataService.save(schedule);
    
    Long scheduleId = schedule.getId();
    System.out.println("scheduleId: " + scheduleId);
    
    List<Long> measurementIds = scheduleCreateReqDto.getMeasurementIds();
    
    scheduleMeasurementService.registerAll(scheduleId, measurementIds);
    
    measurementService.createDraft(scheduleId);
    
    return scheduleMapper.toDto(schedule);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<ScheduleTableViewDto> getList() {
    return scheduleMapper.toTableList(scheduleDataService.findAllWithTeamAndStack());
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<ScheduleResDto> getListByStack(Long stackId, List<ScheduleStatus> status) {
    return scheduleMapper.toDtoList(
        scheduleDataService.findSchedulesByStackIdAndStatusIn(stackId, status)
    );
  }
  
  @Override
  @Transactional(readOnly = true)
  public ScheduleDetailResDto getSchedule(Long scheduleId) {
    Schedule schedule = scheduleDataService.findDetailById(scheduleId);
    
    return ScheduleDetailResDto.builder()
        .schedule(scheduleMapper.toDto(schedule))
        .workplace(workplaceMapper.toDto(schedule.getStack().getWorkplace()))
        .company(companyMapper.toDto(schedule.getStack().getWorkplace().getCompany()))
        .stack(stackService.getStack(schedule.getStack().getId()))
        .measurements(scheduleMeasurementMapper.toDtoList(schedule.getMeasurements()))
        .build();
  }
  
  @Override
  public void addMeasurement(Long scheduleId, List<ScheduleMeasurementCreateReqDto> dtos) {
    Schedule schedule = scheduleDataService.findById(scheduleId);
    
    dtos.stream()
        .map(scheduleMeasurementMapper::toEntity)
        .forEach(schedule::addMeasurement);
    
    scheduleDataService.save(schedule);
  }
  
  @Override
  public ScheduleResDto update(Long scheduleId, ScheduleUpdateReqDto dto) {
    Schedule schedule = scheduleDataService.findById(scheduleId);
    
    Stack stack = null;
    if (dto.getStackId() != null) stack = domainEntityResolver.getStackOrThrow(dto.getStackId());
    
    Team team = null;
    if (dto.getTeamId() != null) team = domainEntityResolver.getTeamOrThrow(dto.getTeamId());
    
    return scheduleMapper.toDto(scheduleDataService.save(schedule));
  }
  
  @Override
  public ScheduleResDto updateStatus(Long scheduleId, ScheduleStatusUpdateReqDto dto) {
    Schedule schedule = scheduleDataService.findById(scheduleId);
    schedule.updateStatus(dto);
    return scheduleMapper.toDto(scheduleDataService.save(schedule));
  }
  
  @Override
  public void delete(Long scheduleId) {
    scheduleDataService.deleteById(scheduleId);
    measurementService.deleteDraft(scheduleId);
  }
}
