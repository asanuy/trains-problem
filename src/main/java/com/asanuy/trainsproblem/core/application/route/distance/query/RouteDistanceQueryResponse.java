package com.asanuy.trainsproblem.core.application.route.distance.query;

import com.asanuy.trainsproblem.shared.Validate;

public class RouteDistanceQueryResponse {

  private final Integer distance;

  private RouteDistanceQueryResponse(Integer distance) {
    Validate.isGreaterThanZero(distance, "Distance");
    this.distance = distance;
  }

  public static RouteDistanceQueryResponse valueOf(Integer distance) {
    return new RouteDistanceQueryResponse(distance);
  }

  @Override
  public String toString() {
    return distance.toString();
  }
}
