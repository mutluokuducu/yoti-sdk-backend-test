package com.robotichoover.model;

import java.awt.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoboticHoover {

  private int countStain;
  private Point hooverPosition;
}
