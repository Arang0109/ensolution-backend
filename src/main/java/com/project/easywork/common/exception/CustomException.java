package com.project.easywork.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
  private final ErrorCode errorCode;
  private final String message;
  
  public CustomException(ErrorCode errorCode, String message, Throwable cause) {
    super(cause);
    this.errorCode = errorCode;
    this.message = message;
  }
  
  public CustomException(ErrorCode errorCode, String message) {
    super();
    this.errorCode = errorCode;
    this.message = message;
  }
  
  public CustomException(ErrorCode errorCode) {
    super(errorCode.name());
    this.errorCode = errorCode;
    this.message = errorCode.name();
  }
}