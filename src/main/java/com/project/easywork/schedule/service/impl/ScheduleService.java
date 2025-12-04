package com.project.easywork.schedule.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.service_data.ITeamDataService;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.schedule.domain.dto.ScheduleCreateRequestDto;
import com.project.easywork.schedule.domain.dto.ScheduleResponseDto;
import com.project.easywork.schedule.domain.dto.ScheduleStatusUpdateRequestDto;
import com.project.easywork.schedule.domain.dto.ScheduleUpdateRequestDto;
import com.project.easywork.schedule.domain.persistance.Schedule;
import com.project.easywork.schedule.mapper.ScheduleMapper;
import com.project.easywork.schedule.service.IScheduleService;
import com.project.easywork.schedule.service_data.IScheduleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService implements IScheduleService {
  
  private final IScheduleDataService scheduleDataService;
  private final IStackDataService stackDataService;
  private final ITeamDataService teamDataService;
  private final ScheduleMapper scheduleMapper;
  
  @Override
  @Transactional(readOnly = true)
  public List<ScheduleResponseDto> getList() {
    return scheduleMapper.toDtoList(scheduleDataService.findAll());
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<ScheduleResponseDto> getListByStack(Long stackId) {
    return scheduleMapper.toDtoList(scheduleDataService.findSchedulesByStackId(stackId));
  }
  
  @Override
  public ScheduleResponseDto register(ScheduleCreateRequestDto dto) {
    Schedule schedule = scheduleMapper.toEntityFromScheduleCreateDto(dto);
    return scheduleMapper.toDto(scheduleDataService.save(schedule));
  }
  
  @Override
  public ScheduleResponseDto update(Long scheduleId, ScheduleUpdateRequestDto dto) {
    Schedule schedule = scheduleDataService.findById(scheduleId);
    
    Stack stack = null;
    if (dto.getStackId() != null) stack = stackDataService.findById(dto.getStackId());
    
    Team team = null;
    if (dto.getTeamId() != null) team = teamDataService.findById(dto.getTeamId());
    
    schedule.update(stack, team, dto);
    return scheduleMapper.toDto(scheduleDataService.save(schedule));
  }
  
  @Override
  public ScheduleResponseDto updateStatus(Long scheduleId, ScheduleStatusUpdateRequestDto dto) {
    Schedule schedule = scheduleDataService.findById(scheduleId);
    schedule.updateStatus(dto);
    return scheduleMapper.toDto(scheduleDataService.save(schedule));
  }
  
  @Override
  public void delete(Long scheduleId) {
    scheduleDataService.deleteById(scheduleId);
  }
}
