package com.project.easywork.client.validator;

import com.project.easywork.client.domain.dto.stack.StackCreateD;
import com.project.easywork.client.domain.dto.stack.StackUpdateD;
import com.project.easywork.client.repository.StackRepository;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StackValidator {
  
  private final StackRepository stackRepository;
  
  public void validateForCreate(StackCreateD dto) {
    validateDuplicate(
        dto.getName(),
        value -> stackRepository
            .existsByWorkplaceIdAndName(
                dto.getWorkplaceId(),
                value
            ),
        "이미 존재하는 사업장입니다."
    );
    
    validateDuplicate(
        dto.getSemsNumber(),
        stackRepository::existsBySemsNumber,
        "이미 존재하는 Sems 번호입니다."
    );
  }
  
  public void validateForUpdate(Long stackId, StackUpdateD dto) {
    validateDuplicateForUpdate(
        dto.getSemsNumber(),
        value -> stackRepository.existsBySemsNumberAndIdNot(value, stackId),
        "이미 존재하는 Sems 번호입니다."
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