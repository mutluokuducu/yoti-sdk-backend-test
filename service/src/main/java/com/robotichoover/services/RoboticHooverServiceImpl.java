package com.robotichoover.services;

import com.robotichoover.dto.RoboticHooverInputs;
import com.robotichoover.dto.request.RobotHooverRequest;
import com.robotichoover.dto.response.RoboticHooverResponse;
import com.robotichoover.processor.RobotHooverProcessor;
import com.robotichoover.util.RoboticHooverUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RoboticHooverServiceImpl implements RoboticHooverService {

  @Autowired
  private RoboticHooverUtil roboticHooverUtil;

  @Autowired
  private RobotHooverProcessor robotHooverProcessor;


  @Override
  public RoboticHooverResponse runningHoover(RobotHooverRequest robotHooverRequest) {
    log.info("Hoover starting");

    RoboticHooverInputs validateRoboticHoover = roboticHooverUtil
        .validateRoboticHoover(robotHooverRequest);

    return robotHooverProcessor.process(validateRoboticHoover);
  }
}
