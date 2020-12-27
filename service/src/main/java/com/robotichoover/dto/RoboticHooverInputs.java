package com.robotichoover.dto;

import java.awt.Point;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RoboticHooverInputs {

  private final Point roomSize;
  private final Point hooverInitialPosition;
  private final Set<Point> patchesPositions;
  private final List<Character> directionCommands;
}
