package com.asanuy.trainsproblem.core.application.route.shortest.query;

import com.asanuy.trainsproblem.core.domain.model.Distance;

public class ShortestRouteQueryResponseMapper {

  public static ShortestRouteQueryResponse of(Distance distance) {
    return ShortestRouteQueryResponse.valueOf(distance.value());
  }
}
