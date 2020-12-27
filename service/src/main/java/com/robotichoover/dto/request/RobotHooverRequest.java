package com.robotichoover.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robotichoover.dto.RoboticHooverBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RobotHooverRequest extends RoboticHooverBase {

  @JsonProperty(value = "roomSize")
  private int[] roomSize;
  @JsonProperty(value = "patches")
  private int[][] patchesCoordinate;
  @JsonProperty(value = "instructions")
  private String instructions;

}
