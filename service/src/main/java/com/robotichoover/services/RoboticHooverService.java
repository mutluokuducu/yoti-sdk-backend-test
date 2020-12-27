package com.robotichoover.services;

import com.robotichoover.dto.request.RobotHooverRequest;
import com.robotichoover.dto.response.RoboticHooverResponse;

public interface RoboticHooverService {

  RoboticHooverResponse runningHoover(RobotHooverRequest robotHooverRequest);
}
