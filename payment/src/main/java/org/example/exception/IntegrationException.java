package org.example.exception;

import lombok.Getter;

@Getter
public class IntegrationException extends RuntimeException {

  private final String errorCode;
  private final String errorMessage;

  public IntegrationException(String message, String errorCode, String errorMessage) {
    super(message);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

}
