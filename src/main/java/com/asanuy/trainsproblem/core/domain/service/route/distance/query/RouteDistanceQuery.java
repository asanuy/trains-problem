package com.asanuy.trainsproblem.core.domain.service.route.distance.query;

import com.asanuy.trainsproblem.core.domain.model.graph.Graph;
import com.asanuy.trainsproblem.core.domain.model.graph.Route;
import com.asanuy.trainsproblem.shared.Validate;

public class RouteDistanceQuery {

  private final Graph graph;
  private final Route route;

  private RouteDistanceQuery(Graph graph, Route route) {
    Validate.isNotNull(graph, "Graph");
    Validate.isNotNull(route, "Route");
    this.graph = graph;
    this.route = route;
  }

  public static RouteDistanceQuery of(Graph graph, Route route) {
    return new RouteDistanceQuery(graph, route);
  }

  public Graph graph() {
    return graph;
  }

  public Route route() {
    return route;
  }
}
