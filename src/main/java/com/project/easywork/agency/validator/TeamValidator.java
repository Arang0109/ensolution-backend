package com.project.easywork.agency.validator;

import com.project.easywork.agency.dto.TeamDto;
import com.project.easywork.agency.repository.TeamRepository;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamValidator {
  
  private final TeamRepository teamRepository;
  
  public void validateSave(TeamDto teamDto) {
    
    if (teamDto.getTeamId() != null) {
      throw new CustomException(ErrorCode.BAD_REQUEST, "등록 시 TEAM_ID는 지정할 수 없습니다.");
    }
    
    validateDuplicateTeamName(teamDto.getTeamName());
  }
  
  public void validateUpdate(TeamDto teamDto) {
    validateFind(teamDto.getTeamId());
    
    String teamName = teamDto.getTeamName();
    if (teamName == null || teamName.isBlank()) {
      throw new CustomException(ErrorCode.BAD_REQUEST, "팀 이름은 비어 있을 수 없습니다.");
    }
    
    if (teamRepository.existsByTeamNameAndTeamIdNot(teamName, teamDto.getTeamId())) {
      throw new CustomException(ErrorCode.CONFLICT, "이미 사용 중인 팀 이름입니다.");
    }
  }
  
  public void validateFind(Long teamId) {
    if (teamId == null) {
      throw new CustomException(ErrorCode.BAD_REQUEST, "TEAM_ID는 null일 수 없습니다.");
    }
    
    if (!teamRepository.existsByTeamId(teamId)) {
      throw new CustomException(ErrorCode.NOT_FOUND, "존재하지 않는 TEAM_ID 입니다.");
    }
  }
  
  private void validateDuplicateTeamName(String teamName) {
    if (teamName == null || teamName.isBlank()) {
      throw new CustomException(ErrorCode.BAD_REQUEST, "팀 이름은 비어 있을 수 없습니다.");
    }
    
    if (teamRepository.existsByTeamName(teamName)) {
      throw new CustomException(ErrorCode.CONFLICT, "중복된 TEAM_NAME 입니다.");
    }
  }
}
