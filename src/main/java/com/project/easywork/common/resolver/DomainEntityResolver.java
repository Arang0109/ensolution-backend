package com.project.easywork.common.resolver;

import com.project.easywork.client.domain.persistance.Company;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.service_data.ICompanyDataService;
import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.client.service_data.IWorkplaceDataService;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainEntityResolver {
  
  private final ICompanyDataService companyDataService;
  private final IWorkplaceDataService workplaceDataService;
  private final IStackDataService stackDataService;
  
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
}