package com.project.easywork.common.resolver;

import com.project.easywork.client.domain.persistance.Company;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.client.domain.persistance.Workplace;
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
}