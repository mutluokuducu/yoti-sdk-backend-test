package com.robotichoover.service;

import static com.robotichoover.constant.RoboticHooverConstants.PATCHES_URL;
import static com.robotichoover.util.ObjectFactory.FAIL_POINT;
import static com.robotichoover.util.ObjectFactory.INITIAL_COORDINATE_AFTER_STAIN;
import static com.robotichoover.util.ObjectFactory.PATCHES_COUNT;
import static com.robotichoover.util.ObjectFactory.buildRobotHooverRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import com.robotichoover.BaseComponentTest;
import com.robotichoover.dto.request.RobotHooverRequest;
import com.robotichoover.dto.response.RoboticHooverResponse;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class RoboticHoverServiceTest extends BaseComponentTest {

  @Test
  public void shouldReturnHoover() {
    RobotHooverRequest robotHooverRequest = buildRobotHooverRequest();

    ResponseEntity<RoboticHooverResponse> response = restTemplate.postForEntity(
        serverUrl.apply(PATCHES_URL),
        robotHooverRequest,
        RoboticHooverResponse.class);

    assertThat(response.getStatusCode()).isEqualTo(OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getInitialCoordinate()).isEqualTo(INITIAL_COORDINATE_AFTER_STAIN);
    assertThat(response.getBody().getPatchesCount()).isEqualTo(PATCHES_COUNT);
  }

  @Test
  public void shouldReturnHoover_GetBadRequest() {
    RobotHooverRequest robotHooverRequest = buildRobotHooverRequest();
    robotHooverRequest.setRoomSize(FAIL_POINT);

    ResponseEntity<RoboticHooverResponse> response = restTemplate.postForEntity(
        serverUrl.apply(PATCHES_URL),
        robotHooverRequest,
        RoboticHooverResponse.class);

    assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
  }
}