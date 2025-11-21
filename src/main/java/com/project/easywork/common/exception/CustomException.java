package com.project.easywork.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
  private final ErrorCode errorCode;
  private final String message; // 직접 전달받은 메시지
  
  public CustomException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
    this.message = message;
  }
  
  public CustomException(ErrorCode errorCode) {
    super(errorCode.name());
    this.errorCode = errorCode;
    this.message = errorCode.name();
  }
}
