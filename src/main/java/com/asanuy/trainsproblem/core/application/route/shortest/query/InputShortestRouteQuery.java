package com.asanuy.trainsproblem.core.application.route.shortest.query;

import com.asanuy.trainsproblem.shared.Validate;
import com.asanuy.trainsproblem.shared.cqs.Query;

public class InputShortestRouteQuery extends Query<ShortestRouteQueryResponse> {

  private final String[] graph;
  private final String origin;
  private final String destination;

  private InputShortestRouteQuery(String[] graph, String origin, String destination) {
    Validate.isNotNull(graph, "Graph");
    Validate.isNotNull(origin, "Origin vertex");
    Validate.isNotNull(destination, "Destination vertex");
    this.graph = graph;
    this.origin = origin;
    this.destination = destination;
  }

  public static InputShortestRouteQuery of(String[] graph, String origin, String destination) {
    return new InputShortestRouteQuery(graph, origin, destination);
  }

  public String[] graph() {
    return graph;
  }

  public String origin() {
    return origin;
  }

  public String destination() {
    return destination;
  }
}
