package com.project.easywork.agency.validator;

import com.project.easywork.agency.domain.dto.TeamCreateRequestDto;
import com.project.easywork.agency.domain.dto.TeamResponseDto;
import com.project.easywork.agency.domain.dto.TeamUpdateRequestDto;
import com.project.easywork.agency.repository.TeamRepository;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamValidator {
  
  private final TeamRepository teamRepository;
  
  public void validate(TeamCreateRequestDto dto) {
    validateDuplicateName(dto.getName());
  }
  
  public void validate(TeamUpdateRequestDto dto) {
    String name = dto.getName();
    if (name == null || name.isBlank()) {
      throw new CustomException(ErrorCode.BAD_REQUEST, "팀 이름은 비어 있을 수 없습니다.");
    }
  }
  
  private void validateDuplicateName(String name) {
    if (name == null || name.isBlank()) {
      throw new CustomException(ErrorCode.BAD_REQUEST, "팀 이름은 비어 있을 수 없습니다.");
    }
    
    if (teamRepository.existsByName(name)) {
      throw new CustomException(ErrorCode.CONFLICT, "중복된 팀 이름 입니다.");
    }
  }
}
