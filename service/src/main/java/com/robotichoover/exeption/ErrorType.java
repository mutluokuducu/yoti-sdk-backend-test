package com.robotichoover.exeption;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public enum ErrorType {
  INTERNAL_ERROR("An internal server error occurred", INTERNAL_SERVER_ERROR),
  INPUT_ROOM_SIZE(
      "RoomSize  greater than 0 (positive number) and input value must be in the format [x,y]",
      BAD_REQUEST),
  INPUT_INITIAL_POSITION(
      "The hoover initial position greater than 0 (positive number), input value must be in the format [x,y] and inside the room area",
      BAD_REQUEST),
  INPUT_PATCHES("Patches input must be in the format [x,y]", BAD_REQUEST),
  INPUT_INSTRUCTIONS(
      "Instructions input must be a sequence of commands direction in the range [N, E, S, W]",
      BAD_REQUEST),
  INPUT_SERVICE("Service, Unexpected input parameter. Please verify the inputs.", BAD_REQUEST),
  NOT_VALID_DIRECTION("Not valid direction command", BAD_REQUEST);

  private String description;
  private HttpStatus httpStatus;

  ErrorType(String description, HttpStatus httpStatus) {

    this.description = description;
    this.httpStatus = httpStatus;
  }
}
