package com.project.easywork.common.exception;

public record FieldErrorResponse(
    String field,
    String message
) {
}
