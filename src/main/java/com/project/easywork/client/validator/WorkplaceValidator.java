package com.project.easywork.client.validator;

import com.project.easywork.client.domain.dto.workplace.WorkplaceCreateRequestDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateRequestDto;
import com.project.easywork.client.repository.WorkplaceRepository;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkplaceValidator {
  
  private final WorkplaceRepository workplaceRepository;
  
  public void validateForCreate(WorkplaceCreateRequestDto dto) {
    validateDuplicate(
        dto.getName(),
        workplaceRepository::existsByName,
        "이미 존재하는 의뢰업체입니다."
    );
    
    validateDuplicate(
        dto.getBizNumber(),
        workplaceRepository::existsByBizNumber,
        "이미 존재하는 사업자번호입니다."
    );
  }
  
  public void validateForUpdate(Long id, WorkplaceUpdateRequestDto dto) {
    validateDuplicateForUpdate(
        dto.getName(),
        value -> workplaceRepository.existsByNameAndIdNot(value, id),
        "이미 존재하는 의뢰업체입니다."
    );
    
    validateDuplicateForUpdate(
        dto.getBizNumber(),
        value -> workplaceRepository.existsByBizNumberAndIdNot(value, id),
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