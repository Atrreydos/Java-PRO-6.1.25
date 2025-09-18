package org.example.controller;

import org.example.dto.ErrorResponseDto;
import org.example.exception.IntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(IntegrationException.class)
  @ResponseStatus(HttpStatus.BAD_GATEWAY)
  public ErrorResponseDto handleIntegrationException(IntegrationException exception) {
    return new ErrorResponseDto(exception.getMessage() + exception.getErrorCode() + " " + exception.getErrorMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponseDto handleRuntimeException(Exception exception) {
    return new ErrorResponseDto(exception.getMessage());
  }

}
