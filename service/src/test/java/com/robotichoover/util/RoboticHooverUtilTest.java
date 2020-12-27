package com.robotichoover.util;

import static com.robotichoover.exeption.ErrorType.INPUT_INITIAL_POSITION;
import static com.robotichoover.exeption.ErrorType.INPUT_INSTRUCTIONS;
import static com.robotichoover.exeption.ErrorType.INPUT_PATCHES;
import static com.robotichoover.exeption.ErrorType.INPUT_ROOM_SIZE;
import static com.robotichoover.util.ObjectFactory.DIRECTION_COMMENDS;
import static com.robotichoover.util.ObjectFactory.FAIL_INSTRUCTION;
import static com.robotichoover.util.ObjectFactory.FAIL_POINT;
import static com.robotichoover.util.ObjectFactory.INITIAL_COORDINATE_X;
import static com.robotichoover.util.ObjectFactory.INITIAL_COORDINATE_Y;
import static com.robotichoover.util.ObjectFactory.ROOM_SIZE_X;
import static com.robotichoover.util.ObjectFactory.ROOM_SIZE_Y;
import static com.robotichoover.util.ObjectFactory.buildPatchesList;
import static com.robotichoover.util.ObjectFactory.buildRobotHooverRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.robotichoover.dto.RoboticHooverInputs;
import com.robotichoover.dto.request.RobotHooverRequest;
import com.robotichoover.exeption.RoboticHooverServiceException;
import java.awt.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoboticHooverUtilTest {

  private final RoboticHooverUtil roboticHooverUtil = new RoboticHooverUtil();

  @Test
  void roboticHoover_ShouldValidateInputs() {
    RobotHooverRequest robotHooverRequest = buildRobotHooverRequest();

    assertThatCode(() -> {
      RoboticHooverInputs roboticHoover = roboticHooverUtil
          .validateRoboticHoover(robotHooverRequest);
      assertThat(roboticHoover.getDirectionCommands()).isEqualTo(DIRECTION_COMMENDS);
      assertThat(roboticHoover.getHooverInitialPosition())
          .isEqualTo(new Point(INITIAL_COORDINATE_X, INITIAL_COORDINATE_Y));
      assertThat(roboticHoover.getPatchesPositions()).isEqualTo(buildPatchesList());
      assertThat(roboticHoover.getRoomSize()).isEqualTo(new Point(ROOM_SIZE_X, ROOM_SIZE_Y));
    }).doesNotThrowAnyException();
  }

  @Test
  void roboticHoover_ShouldValidateInputs_ThrowInputRoomSizeException() {
    RobotHooverRequest robotHooverRequest = buildRobotHooverRequest();
    robotHooverRequest.setRoomSize(FAIL_POINT);

    RoboticHooverServiceException exceptionThrown =
        assertThrows(
            RoboticHooverServiceException.class,
            () ->
                roboticHooverUtil
                    .validateRoboticHoover(robotHooverRequest));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(INPUT_ROOM_SIZE);
  }

  @Test
  void roboticHoover_ShouldValidateInputs_ThrowInitialSizeException() {
    RobotHooverRequest robotHooverRequest = buildRobotHooverRequest();
    robotHooverRequest.setInitialCoordinate(FAIL_POINT);

    RoboticHooverServiceException exceptionThrown =
        assertThrows(
            RoboticHooverServiceException.class,
            () ->
                roboticHooverUtil
                    .validateRoboticHoover(robotHooverRequest));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(INPUT_INITIAL_POSITION);
  }

  @Test
  void roboticHoover_ShouldValidateInputs_ThrowPatchesException() {
    RobotHooverRequest robotHooverRequest = buildRobotHooverRequest();
    robotHooverRequest.setPatchesCoordinate(null);

    RoboticHooverServiceException exceptionThrown =
        assertThrows(
            RoboticHooverServiceException.class,
            () ->
                roboticHooverUtil
                    .validateRoboticHoover(robotHooverRequest));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(INPUT_PATCHES);
  }

  @Test
  void roboticHoover_ShouldValidateInputs_ThrowInstructionsException() {
    RobotHooverRequest robotHooverRequest = buildRobotHooverRequest();
    robotHooverRequest.setInstructions(FAIL_INSTRUCTION);

    RoboticHooverServiceException exceptionThrown =
        assertThrows(
            RoboticHooverServiceException.class,
            () ->
                roboticHooverUtil
                    .validateRoboticHoover(robotHooverRequest));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(INPUT_INSTRUCTIONS);
  }
}
