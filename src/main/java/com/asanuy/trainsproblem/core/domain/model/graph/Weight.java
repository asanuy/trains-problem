package com.asanuy.trainsproblem.core.domain.model.graph;

import com.asanuy.trainsproblem.shared.Validate;

public class Weight {

  private final Integer value;

  private Weight(Integer value) {
    Validate.isGreaterThanZero(value, "Weight");
    this.value = value;
  }

  public static Weight valueOf(Character c) {
    return new Weight(Integer.valueOf(Character.toString(c)));
  }

  public Integer value() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
