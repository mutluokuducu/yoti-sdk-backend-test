package com.robotichoover.exeption;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.robotichoover.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(RoboticHooverServiceException.class)
  @Order(HIGHEST_PRECEDENCE)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleAdapterException(
      RoboticHooverServiceException exception) {
    ErrorType errorMessage = exception.getErrorType();
    HttpStatus httpStatus = errorMessage.getHttpStatus();
    return new ResponseEntity<>(
        ErrorResponse.builder()
            .error(Error.builder()
                .code(httpStatus.value())
                .description("External service call error:"
                             + " http status:[" + httpStatus.value() + "]"
                             + ", message:[" + errorMessage.getDescription() + "]")
                .build())
            .build(),
        httpStatus);
  }

  @ExceptionHandler(Exception.class)
  @Order(HIGHEST_PRECEDENCE)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleException(
      Exception exception) {
    return new ResponseEntity<>(
        ErrorResponse.builder()
            .error(Error.builder()
                .code(INTERNAL_SERVER_ERROR.value())
                .description(exception.getMessage())
                .build())
            .build(),
        INTERNAL_SERVER_ERROR);
  }
}