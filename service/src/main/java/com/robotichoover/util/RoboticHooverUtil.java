package com.robotichoover.util;

import static com.robotichoover.exeption.ErrorType.INPUT_INITIAL_POSITION;
import static com.robotichoover.exeption.ErrorType.INPUT_INSTRUCTIONS;
import static com.robotichoover.exeption.ErrorType.INPUT_PATCHES;
import static com.robotichoover.exeption.ErrorType.INPUT_ROOM_SIZE;

import com.robotichoover.dto.RoboticHooverInputs;
import com.robotichoover.dto.request.RobotHooverRequest;
import com.robotichoover.exeption.RoboticHooverServiceException;
import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RoboticHooverUtil {

  private static final String COORDINATE_DIRECTIONS = "[^NESW]";

  public RoboticHooverInputs validateRoboticHoover(RobotHooverRequest robotHooverRequest) {
    log.info("Validation Robotic Hoover");
    final int[] roomSize = robotHooverRequest.getRoomSize();
    validRoomSize(roomSize);
    final int[] initialCoordinate = robotHooverRequest.getInitialCoordinate();
    validateInitialSizePosition(initialCoordinate, roomSize);
    final int[][] patches = robotHooverRequest.getPatchesCoordinate();
    final Set<Point> patchesInput = validateAndConvertPatchesInput(patches, roomSize);
    final List<Character> directionCommands = validateAndConvertInstructionsInput(
        robotHooverRequest.getInstructions());

    return RoboticHooverInputs.builder()
        .roomSize(new Point(roomSize[0], roomSize[1]))
        .hooverInitialPosition(new Point(initialCoordinate[0], initialCoordinate[1]))
        .patchesPositions(patchesInput)
        .directionCommands(directionCommands)
        .build();
  }

  private void validRoomSize(int[] roomSize) {
    if (roomSize.length == 0 || roomSize[0] < 1 || roomSize[1] < 1) {
      throw new RoboticHooverServiceException(INPUT_ROOM_SIZE);
    }
  }

  private void validateInitialSizePosition(int[] coordinate, int[] roomSize) {
    if (coordinate.length != 2 || coordinate[0] > roomSize[0]
        || coordinate[1] > roomSize[1] || coordinate[0] < 0 || coordinate[1] < 0) {
      throw new RoboticHooverServiceException(INPUT_INITIAL_POSITION);
    }
  }

  protected Set<Point> validateAndConvertPatchesInput(int[][] patches, int[] roomSize) {
    if (null != patches) {
      Set<Point> patchesSet = new HashSet<>();
      for (int[] patch : patches) {
        if (patch.length == 2) {
          int currentX = patch[0];
          int currentY = patch[1];
          if (currentX < roomSize[0] && currentY < roomSize[1]) {
            patchesSet.add(new Point(currentX, currentY));
          }
        }
      }
      return patchesSet;
    }
    throw new RoboticHooverServiceException(INPUT_PATCHES);
  }

  protected List<Character> validateAndConvertInstructionsInput(String instructions) {

    Pattern pattern = Pattern.compile(COORDINATE_DIRECTIONS);
    boolean patternMatcher = pattern.matcher(instructions.toUpperCase()).find();
    List<Character> instructionsCharList;
    if (!patternMatcher && !instructions.isBlank()) {
      instructionsCharList = instructions.toUpperCase().chars()
          .mapToObj(e -> (char) e)
          .collect(Collectors.toList());
    } else {
      throw new RoboticHooverServiceException(INPUT_INSTRUCTIONS);
    }
    return instructionsCharList;
  }
}
