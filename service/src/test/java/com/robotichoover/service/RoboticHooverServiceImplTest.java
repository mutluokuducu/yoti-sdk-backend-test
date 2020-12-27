package com.robotichoover.service;

import static com.robotichoover.exeption.ErrorType.INPUT_ROOM_SIZE;
import static com.robotichoover.util.ObjectFactory.FAIL_POINT;
import static com.robotichoover.util.ObjectFactory.INITIAL_COORDINATE_AFTER_STAIN;
import static com.robotichoover.util.ObjectFactory.PATCHES_COUNT;
import static com.robotichoover.util.ObjectFactory.buildRobotHooverRequest;
import static com.robotichoover.util.ObjectFactory.buildRoboticHooverInputs;
import static com.robotichoover.util.ObjectFactory.buildRoboticHooverResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.robotichoover.dto.RoboticHooverInputs;
import com.robotichoover.dto.request.RobotHooverRequest;
import com.robotichoover.dto.response.RoboticHooverResponse;
import com.robotichoover.exeption.RoboticHooverServiceException;
import com.robotichoover.processor.RobotHooverProcessor;
import com.robotichoover.services.RoboticHooverServiceImpl;
import com.robotichoover.util.RoboticHooverUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoboticHooverServiceImplTest {

  @Mock
  private RoboticHooverUtil roboticHooverUtil;

  @Mock
  private RobotHooverProcessor robotHooverProcessor;

  @InjectMocks
  private RoboticHooverServiceImpl roboticHooverService;

  @Test
  void getRunningHoover_ShouldReturnRoboticHooverResponse() {

    RobotHooverRequest robotHooverRequest = buildRobotHooverRequest();
    RoboticHooverResponse roboticHooverResponse = buildRoboticHooverResponse();
    RoboticHooverInputs roboticHooverInputs = buildRoboticHooverInputs();

    when(roboticHooverUtil.validateRoboticHoover(robotHooverRequest))
        .thenReturn(roboticHooverInputs);

    when(robotHooverProcessor.process(roboticHooverInputs))
        .thenReturn(roboticHooverResponse);

    assertThatCode(() -> {
      RoboticHooverResponse response = roboticHooverService.runningHoover(robotHooverRequest);
      assertThat(response.getPatchesCount()).isEqualTo(PATCHES_COUNT);
      assertThat(response.getInitialCoordinate()).isEqualTo(INITIAL_COORDINATE_AFTER_STAIN);
    }).doesNotThrowAnyException();
  }

  @Test
  void getRunningHoover_ShouldThrowException() {
    RobotHooverRequest robotHooverRequest = buildRobotHooverRequest();
    robotHooverRequest.setRoomSize(FAIL_POINT);

    doThrow(new RoboticHooverServiceException(INPUT_ROOM_SIZE))
        .when(roboticHooverUtil)
        .validateRoboticHoover(robotHooverRequest);

    RoboticHooverServiceException exceptionThrown =
        assertThrows(
            RoboticHooverServiceException.class,
            () ->
                roboticHooverService.runningHoover(robotHooverRequest));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(INPUT_ROOM_SIZE);
  }
}