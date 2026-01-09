package com.project.easywork.pollutant.validator;

import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.pollutant.domain.dto.PollutantCreateD;
import com.project.easywork.pollutant.repository.PollutantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PollutantValidator {
  
  private final PollutantRepository pollutantRepository;
  
  public void validate(PollutantCreateD dto) {
    validateDuplicateName(
        dto.getNameKr(),
        dto.getNameEn()
    );
  }
  
  private void validateDuplicateName(String nameKr, String nameEn) {
    if (pollutantRepository.existsByNameKr(nameKr) || pollutantRepository.existsByNameEn(nameEn)) {
      throw new CustomException(ErrorCode.CONFLICT, "이미 등록된 측정물질입니다.");
    }
  }
}
