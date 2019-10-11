package com.asanuy.trainsproblem.core.application.search.hops.query.max;

import com.asanuy.trainsproblem.core.application.search.SearchQueryResponse;
import com.asanuy.trainsproblem.shared.Validate;
import com.asanuy.trainsproblem.shared.cqs.Query;

public class InputMaxHopsSearchQuery extends Query<SearchQueryResponse> {

  private final String[] graph;
  private final String origin;
  private final String destination;
  private final int hops;

  private InputMaxHopsSearchQuery(String[] graph, String origin, String destination, int hops) {
    Validate.isNotNull(graph, "Graph");
    Validate.isNotNull(origin, "Origin vertex");
    Validate.isNotNull(destination, "Destination vertex");
    Validate.isGreaterThanZero(hops, "Hops");
    this.graph = graph;
    this.origin = origin;
    this.destination = destination;
    this.hops = hops;
  }

  public static InputMaxHopsSearchQuery of(
      String[] graph, String origin, String destination, int hops) {
    return new InputMaxHopsSearchQuery(graph, origin, destination, hops);
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

  public int hops() {
    return hops;
  }
}
