package com.project.easywork.schedule.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.service_data.ITeamDataService;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.common.constant.ScheduleStatus;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.schedule.domain.dto.*;
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
  private final IMeasurementService measurementService;
  private final ScheduleMapper scheduleMapper;
  
  @Override
  @Transactional(readOnly = true)
  public List<ScheduleTableViewDto> getList() {
    List<Schedule> list = scheduleDataService.findAllWithTeamAndStack();
    Schedule s = list.get(0);
    
    System.out.println("stack class = " + s.getStack().getClass());
    System.out.println("team class = " + s.getTeam().getClass());
    
    System.out.println("stack id = " + s.getStack().getId());
    System.out.println("team id = " + s.getTeam().getId());
    System.out.println(scheduleMapper.toTableList(list));
    return scheduleMapper.toTableList(scheduleDataService.findAllWithTeamAndStack());
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<ScheduleResponseDto> getListByStack(Long stackId, List<ScheduleStatus> status) {
    return scheduleMapper.toDtoList(
        scheduleDataService.findSchedulesByStackIdAndStatusIn(stackId, status)
    );
  }
  
  @Override
  public ScheduleResponseDto register(ScheduleCreateRequestDto dto) {
    Schedule schedule = scheduleMapper.toEntityFromScheduleCreateDto(dto);
    
    schedule = scheduleDataService.save(schedule);
    
    measurementService.createDraft(schedule.getId());
    
    return scheduleMapper.toDto(schedule);
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
    measurementService.deleteDraft(scheduleId);
  }
}
