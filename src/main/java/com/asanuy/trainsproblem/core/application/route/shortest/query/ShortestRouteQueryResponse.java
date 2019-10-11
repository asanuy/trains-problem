package com.asanuy.trainsproblem.core.application.route.shortest.query;

import com.asanuy.trainsproblem.shared.Validate;

public class ShortestRouteQueryResponse {

  private final Integer distance;

  private ShortestRouteQueryResponse(Integer distance) {
    Validate.isGreaterThanZero(distance, "Distance");
    this.distance = distance;
  }

  public static ShortestRouteQueryResponse valueOf(Integer distance) {
    return new ShortestRouteQueryResponse(distance);
  }

  @Override
  public String toString() {
    return distance.toString();
  }
}
