package com.project.easywork.common.validator;

import com.project.easywork.common.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class ValidationUtils {
  
  public static <T> ResponseEntity<ApiResponse<T>> handleBindingErrors(BindingResult result) {
    String errorMsg = result.getFieldErrors().stream()
        .map(err -> err.getField() + ": " + err.getDefaultMessage())
        .collect(Collectors.joining(", "));
    
    ApiResponse<T> error = new ApiResponse<>(false, errorMsg, null);
    return ResponseEntity.badRequest().body(error);
  }
}