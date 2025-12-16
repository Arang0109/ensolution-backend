package com.project.easywork.schedule.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.service_data.ITeamDataService;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.mapper.CompanyMapper;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.client.service.IStackService;
import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.schedule.domain.ScheduleStatus;
import com.project.easywork.measurement.service.IMeasurementService;
import com.project.easywork.schedule.domain.dto.*;
import com.project.easywork.schedule.domain.persistance.Schedule;
import com.project.easywork.schedule.domain.persistance.SchedulePollutant;
import com.project.easywork.schedule.mapper.ScheduleMapper;
import com.project.easywork.schedule.mapper.SchedulePollutantMapper;
import com.project.easywork.schedule.service.IScheduleService;
import com.project.easywork.schedule.service_data.IScheduleDataService;
import com.project.easywork.schedule.service_data.ISchedulePollutantDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService implements IScheduleService {
  
  private final IScheduleDataService scheduleDataService;
  private final ISchedulePollutantDataService schedulePollutantDataService;
  private final IStackDataService stackDataService;
  private final ITeamDataService teamDataService;
  
  private final IStackService stackService;
  private final IMeasurementService measurementService;
  
  private final ScheduleMapper scheduleMapper;
  private final SchedulePollutantMapper schedulePollutantMapper;
  private final CompanyMapper companyMapper;
  private final WorkplaceMapper workplaceMapper;
  
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
        .measurements(schedulePollutantMapper.toDtoList(schedule.getPollutants()))
        .build();
  }
  
  @Override
  public ScheduleResDto register(ScheduleCreateReqDto scheduleCreateReqDto) {
    Schedule schedule = scheduleMapper.toEntityFromScheduleCreateDto(scheduleCreateReqDto);
    
    schedule = scheduleDataService.save(schedule);
    Long scheduleId = schedule.getId();
    
    List<SchedulePollutantCreateReqDto> linkDtos =
        scheduleCreateReqDto.getMeasurementIds().stream()
            .map(measurementId ->
                SchedulePollutantCreateReqDto.builder()
                    .scheduleId(scheduleId)
                    .stackMeasurementId(measurementId)
                    .build()
            )
            .toList();
    
    List<SchedulePollutant> schedulePollutants =
        schedulePollutantMapper.toEntityList(linkDtos);
    
    schedulePollutantDataService.saveAll(schedulePollutants);
    
    measurementService.createDraft(schedule.getId());
    
    return scheduleMapper.toDto(schedule);
  }
  
  @Override
  public void addMeasurement(Long scheduleId, List<SchedulePollutantCreateReqDto> dtos) {
    Schedule schedule = scheduleDataService.findById(scheduleId);
    
    dtos.stream()
        .map(schedulePollutantMapper::toEntity)
        .forEach(schedule::addPollutant);
    
    scheduleDataService.save(schedule);
  }
  
  @Override
  public ScheduleResDto update(Long scheduleId, ScheduleUpdateReqDto dto) {
    Schedule schedule = scheduleDataService.findById(scheduleId);
    
    Stack stack = null;
    if (dto.getStackId() != null) stack = stackDataService.findById(dto.getStackId());
    
    Team team = null;
    if (dto.getTeamId() != null) team = teamDataService.findById(dto.getTeamId());
    
    schedule.update(stack, team, dto);
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
