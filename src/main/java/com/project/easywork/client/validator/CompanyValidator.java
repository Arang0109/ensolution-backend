package com.project.easywork.client.validator;

import com.project.easywork.client.domain.dto.company.CompanyCreateD;
import com.project.easywork.client.domain.dto.company.CompanyUpdateD;
import com.project.easywork.client.repository.CompanyRepository;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyValidator {
  
  private final CompanyRepository companyRepository;
  
  public void validateForCreate(CompanyCreateD dto) {
    validateDuplicate(
        dto.getName(),
        companyRepository::existsByName,
        "이미 존재하는 의뢰업체입니다."
    );
    
    validateDuplicate(
        dto.getBizNumber(),
        companyRepository::existsByBizNumber,
        "이미 존재하는 사업자번호입니다."
    );
  }
  
  public void validateForUpdate(Long id, CompanyUpdateD dto) {
    validateDuplicateForUpdate(
        dto.getName(),
        value -> companyRepository.existsByNameAndIdNot(value, id),
        "이미 존재하는 의뢰업체입니다."
    );
    
    validateDuplicateForUpdate(
        dto.getBizNumber(),
        value -> companyRepository.existsByBizNumberAndIdNot(value, id),
        "이미 존재하는 사업자번호입니다."
    );
  }
  
  private void validateDuplicate(
      String value,
      java.util.function.Predicate<String> existsPredicate,
      String message
  ) {
    if (existsPredicate.test(value)) {
      throw new CustomException(ErrorCode.CONFLICT, message);
    }
  }
  
  private void validateDuplicateForUpdate(
      String value,
      java.util.function.Predicate<String> existsPredicate,
      String message
  ) {
    if (value == null) return;
    
    if (existsPredicate.test(value)) {
      throw new CustomException(ErrorCode.CONFLICT, message);
    }
  }
}