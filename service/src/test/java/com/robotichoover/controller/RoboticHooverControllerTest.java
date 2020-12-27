package com.robotichoover.controller;

import static com.robotichoover.constant.RoboticHooverConstants.PATCHES_URL;
import static com.robotichoover.util.JsonUtils.objectToJson;
import static com.robotichoover.util.ObjectFactory.INITIAL_COORDINATE_AFTER_STAIN;
import static com.robotichoover.util.ObjectFactory.PATCHES_COUNT;
import static com.robotichoover.util.ObjectFactory.buildRobotHooverRequest;
import static com.robotichoover.util.ObjectFactory.buildRoboticHooverResponse;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.robotichoover.dto.request.RobotHooverRequest;
import com.robotichoover.exeption.GlobalExceptionHandler;
import com.robotichoover.services.RoboticHooverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class RoboticHooverControllerTest {

  private MockMvc mockMvc;

  @Mock
  private RoboticHooverService roboticHooverService;

  @InjectMocks
  private RoboticHooverController roboticHooverController;

  @BeforeEach
  public void init() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(roboticHooverController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Test
  void roboticHoover_shouldFindStain() throws Exception {
    RobotHooverRequest robotHooverRequest = buildRobotHooverRequest();

    when(roboticHooverService
        .runningHoover(robotHooverRequest)).thenReturn(buildRoboticHooverResponse());

    this.mockMvc
        .perform(
            post(PATCHES_URL)
                .content(objectToJson(robotHooverRequest))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.coords[0]").value(INITIAL_COORDINATE_AFTER_STAIN[0]))
        .andExpect(jsonPath("$.coords[1]").value(INITIAL_COORDINATE_AFTER_STAIN[1]))
        .andExpect(jsonPath("$.patches").value(PATCHES_COUNT));
  }
}