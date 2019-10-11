package com.asanuy.trainsproblem.core.application.route.distance.query;

import com.asanuy.trainsproblem.core.domain.model.Distance;

public class RouteDistanceQueryResponseMapper {

  public static RouteDistanceQueryResponse of(Distance distance) {
    return RouteDistanceQueryResponse.valueOf(distance.value());
  }
}
