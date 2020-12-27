package com.robotichoover.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robotichoover.dto.RoboticHooverBase;
import com.robotichoover.exeption.ErrorType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoboticHooverResponse extends RoboticHooverBase {

  @JsonInclude(Include.NON_NULL)
  private List<ErrorType> errors;
  @JsonProperty(value = "patches")
  private int patchesCount;

}
