package com.project.easywork.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  SCHEDULE_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 해당 사업장·팀·측정일에 등록된 일정이 존재합니다."),
  
  CONFLICT(HttpStatus.CONFLICT, "이미 존재하는 데이터입니다."),
  NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
  
  private final HttpStatus status;
  private final String message;
}