package com.project.easywork.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  CONFLICT(HttpStatus.CONFLICT),
  NOT_FOUND(HttpStatus.NOT_FOUND),
  BAD_REQUEST(HttpStatus.BAD_REQUEST);
  
  private final HttpStatus status;
}