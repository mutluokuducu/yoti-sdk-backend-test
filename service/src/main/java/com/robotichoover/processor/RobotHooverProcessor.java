package com.robotichoover.processor;

import static com.robotichoover.exeption.ErrorType.NOT_VALID_DIRECTION;

import com.robotichoover.dto.RoboticHooverInputs;
import com.robotichoover.dto.response.RoboticHooverResponse;
import com.robotichoover.exeption.RoboticHooverServiceException;
import com.robotichoover.model.RoboticHoover;
import java.awt.Point;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RobotHooverProcessor {

  public RoboticHooverResponse process(RoboticHooverInputs roboticHooverInputs) {
    log.info("Executing direction commands: " + roboticHooverInputs.getDirectionCommands());

    RoboticHoover roboticHoover = RoboticHoover.builder().build();
    roboticHoover.setHooverPosition(roboticHooverInputs.getHooverInitialPosition());
    roboticHoover.setCountStain(0);

    Set<Point> stains = roboticHooverInputs.getPatchesPositions();
    List<Character> characters = roboticHooverInputs.getDirectionCommands();
    Point roomSize = roboticHooverInputs.getRoomSize();

    characters
        .forEach(command -> {
          Point directionPoint = commandDirection(roboticHoover.getHooverPosition(), roomSize,
              command);
          roboticHoover.setHooverPosition(directionPoint);

          if (isStainMatchHooverPosition(roboticHoover.getHooverPosition(), stains)) {
            roboticHoover.setCountStain(roboticHoover.getCountStain() + 1);
            stains.remove(roboticHoover.getHooverPosition());
          }
        });

    return RoboticHooverResponse.builder()
        .patchesCount(roboticHoover.getCountStain())
        .initialCoordinate(
            new int[]{roboticHoover.getHooverPosition().x, roboticHoover.getHooverPosition().y})
        .build();
  }

  private Point commandDirection(Point hooverPosition, Point roomSize, Character command) {
    switch (Character.toUpperCase(command)) {
      case 'N':
        return moveNorth(roomSize, hooverPosition);
      case 'E':
        return moveEast(roomSize, hooverPosition);
      case 'S':
        return moveSouth(hooverPosition);
      case 'W':
        return moveWest(hooverPosition);
      default:
        log.error("Not valid direction command: " + command);
    }
    throw new RoboticHooverServiceException(NOT_VALID_DIRECTION);
  }

  private boolean isStainMatchHooverPosition(Point hooverInitialPosition,
      Set<Point> patchesPosition) {
    return patchesPosition
        .stream()
        .anyMatch(stain -> stain.equals(hooverInitialPosition));
  }

  private Point moveNorth(@NotNull Point roomSize, @NotNull Point currentPosition) {
    int upperRoomEdge = roomSize.y;
    if (currentPosition.y < upperRoomEdge) {
      return new Point(currentPosition.x, currentPosition.y + 1);
    }
    return currentPosition;
  }

  private Point moveSouth(@NotNull Point currentPosition) {
    if (currentPosition.y > 0) {
      return new Point(currentPosition.x, currentPosition.y - 1);
    }
    return currentPosition;
  }

  private Point moveWest(@NotNull Point currentPosition) {
    if (currentPosition.x > 0) {
      return new Point(currentPosition.x - 1, currentPosition.y);
    }
    return currentPosition;
  }

  private Point moveEast(@NotNull Point roomSize, @NotNull Point currentPosition) {
    int easternRoomEdge = roomSize.x;
    if (currentPosition.x < easternRoomEdge) {
      return new Point(currentPosition.x + 1, currentPosition.y);
    }
    return currentPosition;
  }
}
