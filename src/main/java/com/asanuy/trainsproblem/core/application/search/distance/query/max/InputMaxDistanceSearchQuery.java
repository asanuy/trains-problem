package com.asanuy.trainsproblem.core.application.search.distance.query.max;

import com.asanuy.trainsproblem.core.application.search.SearchQueryResponse;
import com.asanuy.trainsproblem.shared.Validate;
import com.asanuy.trainsproblem.shared.cqs.Query;

public class InputMaxDistanceSearchQuery extends Query<SearchQueryResponse> {

  private final String[] graph;
  private final String origin;
  private final String destination;
  private final int distance;

  private InputMaxDistanceSearchQuery(
      String[] graph, String origin, String destination, int distance) {
    Validate.isNotNull(graph, "Graph");
    Validate.isNotNull(origin, "Origin vertex");
    Validate.isNotNull(destination, "Destination vertex");
    Validate.isGreaterThanZero(distance, "Distance");
    this.graph = graph;
    this.origin = origin;
    this.destination = destination;
    this.distance = distance;
  }

  public static InputMaxDistanceSearchQuery of(
      String[] graph, String origin, String destination, int distance) {
    return new InputMaxDistanceSearchQuery(graph, origin, destination, distance);
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

  public int distance() {
    return distance;
  }
}
