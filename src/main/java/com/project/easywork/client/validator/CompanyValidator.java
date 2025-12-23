package com.project.easywork.client.validator;

import com.project.easywork.client.domain.dto.company.CompanyCreateRequestDto;
import com.project.easywork.client.domain.dto.company.CompanyUpdateRequestDto;
import com.project.easywork.client.repository.CompanyRepository;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyValidator {
  
  private final CompanyRepository companyRepository;
  
  public void validateForCreate(CompanyCreateRequestDto dto) {
    validateNameNotExists(dto.getName());
    validateBizNumberNotExists(dto.getBizNumber());
  }
  
  public void validateForUpdate(Long companyId, CompanyUpdateRequestDto dto) {
    validateNameNotExistsForUpdate(dto.getName(), companyId);
    validateBizNumberNotExistsForUpdate(dto.getBizNumber(), companyId);
  }
  
  private void validateNameNotExists(String name) {
    if (companyRepository.existsByName(name)) {
      throw new CustomException(ErrorCode.CONFLICT, "이미 존재하는 의뢰업체입니다.");
    }
  }
  
  private void validateBizNumberNotExists(String bizNumber) {
    if (companyRepository.existsByBizNumber(bizNumber)) {
      throw new CustomException(ErrorCode.CONFLICT, "이미 존재하는 사업자번호입니다.");
    }
  }
  
  private void validateNameNotExistsForUpdate(String name, Long companyId) {
    if (name == null) return;
    
    if (companyRepository.existsByNameAndIdNot(name, companyId)) {
      throw new CustomException(ErrorCode.CONFLICT, "이미 존재하는 의뢰업체입니다.");
    }
  }
  
  private void validateBizNumberNotExistsForUpdate(String bizNumber, Long companyId) {
    if (bizNumber == null) return;
    
    if (companyRepository.existsByBizNumberAndIdNot(bizNumber, companyId)) {
      throw new CustomException(ErrorCode.CONFLICT, "이미 존재하는 사업자번호입니다.");
    }
  }
}
