package com.project.easywork.common.resolver;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.domain.entity.Vehicle;
import com.project.easywork.agency.service_data.ITeamDataService;
import com.project.easywork.agency.service_data.IVehicleDataService;
import com.project.easywork.client.domain.persistance.*;
import com.project.easywork.client.service_data.*;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import com.project.easywork.pollutant.service_data.IPollutantDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainEntityResolver {
  
  private final ICompanyDataService companyDataService;
  private final IWorkplaceDataService workplaceDataService;
  private final IStackDataService stackDataService;
  private final IPollutantDataService pollutantDataService;
  private final IStackMeasurementDataService stackMeasurementDataService;
  private final IPreventionDataService preventionDataService;
  private final IFacilityDataService facilityDataService;
  private final ITargetDataService targetDataService;
  private final ITeamDataService teamDataService;
  private final IVehicleDataService vehicleDataService;
  
  public Company getCompanyOrThrow(Long companyId) {
    Company company = companyDataService.findById(companyId);
    if (company == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 의뢰업체를 찾을 수 없습니다.");
    }
    return company;
  }
  
  public Workplace getWorkplaceOrThrow(Long workplaceId) {
    Workplace workplace = workplaceDataService.findById(workplaceId);
    if (workplace == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 사업장을 찾을 수 없습니다.");
    }
    return workplace;
  }
  
  public Stack getStackOrThrow(Long stackId) {
    Stack stack = stackDataService.findById(stackId);
    if(stack == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 측정시설을 찾을 수 없습니다.");
    }
    return stack;
  }
  
  public Pollutant getPollutantOrThrow(Long pollutantId) {
    Pollutant pollutant = pollutantDataService.findById(pollutantId);
    if(pollutant == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 오염물질을 찾을 수 없습니다.");
    }
    return pollutant;
  }
  
  public StackMeasurement getStackMeasurementOrThrow(Long stackMeasurementId) {
    StackMeasurement stackMeasurement = stackMeasurementDataService.findById(stackMeasurementId);
    if(stackMeasurement == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 측정시설의 측정항목을 찾을 수 없습니다.");
    }
    return stackMeasurement;
  }
  
  public Prevention getPreventionOrThrow(Long preventionId) {
    Prevention prevention = preventionDataService.findById(preventionId);
    if(prevention == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 방지시설을 찾을 수 없습니다.");
    }
    return prevention;
  }
  
  public Facility getFacilityOrThrow(Long facilityId) {
    Facility facility = facilityDataService.findById(facilityId);
    if(facility == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 배출시설을 찾을 수 없습니다.");
    }
    return facility;
  }
  
  public Target getTargetOrThrow(Long targetId) {
    Target target = targetDataService.findById(targetId);
    if(target == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 배출시설의 제거항목을 찾을 수 없습니다.");
    }
    return target;
  }
  
  public Team getTeamOrThrow(Long teamId) {
    Team team = teamDataService.findById(teamId);
    if(team == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 팀을 찾을 수 없습니다.");
    }
    return team;
  }
  
  public Vehicle getVehicleOrThrow(Long vehicleId) {
    Vehicle vehicle = vehicleDataService.findById(vehicleId);
    if(vehicle == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 차량의 제거항목을 찾을 수 없습니다.");
    }
    return vehicle;
  }
  
  
}