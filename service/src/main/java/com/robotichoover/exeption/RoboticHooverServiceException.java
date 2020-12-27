package com.robotichoover.exeption;

import lombok.Getter;

@Getter
public class RoboticHooverServiceException extends RuntimeException {

  private final ErrorType errorType;

  public RoboticHooverServiceException(ErrorType errorType) {
    this.errorType = errorType;
  }
}
