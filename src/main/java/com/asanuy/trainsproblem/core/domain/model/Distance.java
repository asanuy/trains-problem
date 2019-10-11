package com.asanuy.trainsproblem.core.domain.model;

import com.asanuy.trainsproblem.shared.Validate;

public class Distance {

  private Integer value;

  private Distance() {
    this.value = 0;
  }

  private Distance(Integer value) {
    Validate.isGreaterThanZero(value, "Distance");
    this.value = value;
  }

  public static Distance zero() {
    return new Distance();
  }

  public static Distance of(Integer value) {
    return new Distance(value);
  }

  public void add(Integer weight) {
    value = Integer.sum(value, weight);
  }

  public Integer value() {
    return value;
  }
}
