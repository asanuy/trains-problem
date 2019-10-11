package com.asanuy.trainsproblem.core.application.route.distance.query;

import com.asanuy.trainsproblem.shared.Validate;
import com.asanuy.trainsproblem.shared.cqs.Query;

public class InputRouteDistanceQuery extends Query<RouteDistanceQueryResponse> {

  private final String[] graph;
  private final String[] route;

  private InputRouteDistanceQuery(String[] graph, String[] route) {
    Validate.isNotNull(graph, "Graph");
    Validate.isNotNull(route, "Route");
    this.graph = graph;
    this.route = route;
  }

  public static InputRouteDistanceQuery of(String[] graph, String[] route) {
    return new InputRouteDistanceQuery(graph, route);
  }

  public String[] graph() {
    return graph;
  }

  public String[] route() {
    return route;
  }
}
