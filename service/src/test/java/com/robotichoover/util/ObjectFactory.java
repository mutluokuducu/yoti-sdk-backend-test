package com.robotichoover.util;

import com.robotichoover.dto.RoboticHooverInputs;
import com.robotichoover.dto.request.RobotHooverRequest;
import com.robotichoover.dto.response.RoboticHooverResponse;
import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ObjectFactory {


  public static final int INITIAL_COORDINATE_X = 1;
  public static final int INITIAL_COORDINATE_Y = 2;
  public static final int INITIAL_COORDINATE_AFTER_STAIN_X = 1;
  public static final int INITIAL_COORDINATE_AFTER_STAIN_Y = 3;
  public static final int[] INITIAL_COORDINATE_AFTER_STAIN = {1, 3};
  public static final int PATCHES_COUNT = 1;
  public static final String FAIL_INSTRUCTION = "NNESEESWNWWA";
  public static final int[] FAIL_POINT = {-5, 5};
  public static final int ROOM_SIZE_X = 5;
  public static final int ROOM_SIZE_Y = 5;
  public static final List<Character> DIRECTION_COMMENDS = Arrays
      .asList('N', 'N', 'E', 'S', 'E', 'E', 'S', 'W', 'N', 'W', 'W');
  private static final int[] INITIAL_COORDINATE = {1, 2};
  private static final int[][] PATCHES = {{1, 0}, {2, 2}, {2, 3}};
  private static final String INSTRUCTION = "NNESEESWNWW";
  private static final int[] ROOM_SIZE = {5, 5};
  private static final String DIRECTIONS_PATTERN = "[^NESW]";


  public static RoboticHooverResponse buildRoboticHooverResponse() {
    return RoboticHooverResponse.builder()
        .initialCoordinate(INITIAL_COORDINATE_AFTER_STAIN)
        .patchesCount(PATCHES_COUNT)
        .build();
  }

  public static RobotHooverRequest buildRobotHooverRequest() {

    return RobotHooverRequest.builder()
        .instructions(INSTRUCTION)
        .patchesCoordinate(PATCHES)
        .roomSize(ROOM_SIZE)
        .initialCoordinate(INITIAL_COORDINATE)
        .build();
  }

  public static RoboticHooverInputs buildRoboticHooverInputs() {
    return RoboticHooverInputs.builder()
        .roomSize(new Point(ROOM_SIZE_X, ROOM_SIZE_Y))
        .hooverInitialPosition(new Point(INITIAL_COORDINATE_X, INITIAL_COORDINATE_Y))
        .patchesPositions(buildPatchesList())
        .directionCommands(DIRECTION_COMMENDS)
        .build();
  }

  public static Set<Point> buildPatchesList() {
    Set<Point> patchesSet = new HashSet<>();
    for (int[] patch : PATCHES) {
      patchesSet.add(new Point(patch[0], patch[1]));
    }
    return patchesSet;
  }
}
