package com.robotichoover.processor;

import static com.robotichoover.util.ObjectFactory.INITIAL_COORDINATE_AFTER_STAIN;
import static com.robotichoover.util.ObjectFactory.PATCHES_COUNT;
import static com.robotichoover.util.ObjectFactory.buildRoboticHooverInputs;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.robotichoover.dto.RoboticHooverInputs;
import com.robotichoover.dto.response.RoboticHooverResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RobotHooverProcessorTest {

  private final RobotHooverProcessor robotHooverProcessor = new RobotHooverProcessor();

  @Test
  void processor_ShouldRoboticHoover() {
    RoboticHooverInputs roboticHooverInputs = buildRoboticHooverInputs();

    assertThatCode(() -> {
      RoboticHooverResponse processorRoboticHoover = robotHooverProcessor
          .process(roboticHooverInputs);

      assertThat(processorRoboticHoover.getPatchesCount()).isEqualTo(PATCHES_COUNT);
      assertThat(processorRoboticHoover.getInitialCoordinate())
          .isEqualTo(INITIAL_COORDINATE_AFTER_STAIN);

    }).doesNotThrowAnyException();
  }
}
