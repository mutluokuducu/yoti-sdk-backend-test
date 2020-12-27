package com.robotichoover.model;

import static com.robotichoover.util.ObjectFactory.buildRoboticHooverInputs;

import com.robotichoover.dto.RoboticHooverInputs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoboticHooverTest {

  private final RoboticHoover roboticHoover = new RoboticHoover();

  @Test
  void roboticHoover_ShouldExecuteCommands() {
    RoboticHooverInputs roboticHooverInputs = buildRoboticHooverInputs();
//
//    assertThatCode(() -> {
//      RoboticHooverResponse roboticHooverExecute = roboticHoover
//          .process(roboticHooverInputs);
//
//      assertThat(roboticHooverExecute.getPatchesCount()).isEqualTo(PATCHES_COUNT);
//      assertThat(roboticHooverExecute.getInitialCoordinate())
//          .isEqualTo(INITIAL_COORDINATE_AFTER_STAIN);
//
//    }).doesNotThrowAnyException();
  }
}
