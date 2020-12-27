package com.robotichoover.controller;

import static com.robotichoover.constant.RoboticHooverConstants.PATCHES_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import com.robotichoover.dto.request.RobotHooverRequest;
import com.robotichoover.dto.response.RoboticHooverResponse;
import com.robotichoover.services.RoboticHooverService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RoboticHooverController {

  @Autowired
  public RoboticHooverService roboticHooverService;

  @ApiOperation(
      value = "Robotic Hoover",
      nickname = "Robotic Hoover",
      response = ResponseEntity.class)
  @PostMapping(
      value = PATCHES_URL,
      consumes = APPLICATION_JSON_UTF8_VALUE,
      produces = APPLICATION_JSON_UTF8_VALUE)
  public HttpEntity<RoboticHooverResponse> roboticHooverResponse(
      @RequestBody RobotHooverRequest robotHooverRequest) {

    return ResponseEntity.ok().body(roboticHooverService.runningHoover(robotHooverRequest));
  }
}
