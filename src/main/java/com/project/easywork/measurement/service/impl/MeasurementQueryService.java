package com.project.easywork.measurement.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.service_data.impl.TeamDataService;
import com.project.easywork.client.domain.persistance.*;
import com.project.easywork.client.service_data.impl.*;
import com.project.easywork.measurement.dto.snapshot.MeasurementSnapshot;
import com.project.easywork.measurement.service.IMeasurementQueryService;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;
import com.project.easywork.user.domain.entity.User;
import com.project.easywork.user.service_data.impl.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementQueryService implements IMeasurementQueryService {
  private final CompanyDataService companyDataService;
  private final WorkplaceDataService workplaceDataService;
  private final StackDataService stackDataService;
  private final StackMeasurementDataService stackMeasurementDataService;
  private final PreventionDataService preventionDataService;
  private final TeamDataService teamDataService;
  private final UserDataService userDataService;
  
  @Override
  public MeasurementSnapshot loadSnapshot(PlanCreateBundleD dto) {
    
    Company company =
        companyDataService.findById(dto.getCompanyId());
    
    Workplace workplace =
        workplaceDataService.findById(dto.getWorkplaceId());
    
    Stack stack =
        stackDataService.findById(dto.getPlan().getStackId());
    
    List<Prevention> preventions =
        preventionDataService.findPreventionsByStackId(stack.getId());
    
    List<StackMeasurement> measurements =
        stackMeasurementDataService.findByIdIn(dto.getPlan().getMeasurementIds());
    
    Team team =
        teamDataService.findById(dto.getPlan().getTeamId());
    
    User senior =
        userDataService.findById(dto.getSeniorUserId());
    
    User junior =
        userDataService.findById(dto.getJuniorUserId());
    
    String vehicleNumber = dto.getVehicleNumber();
    
    return new MeasurementSnapshot(
        company,
        workplace,
        stack,
        preventions,
        measurements,
        team,
        senior,
        junior,
        vehicleNumber
    );
  }
}
