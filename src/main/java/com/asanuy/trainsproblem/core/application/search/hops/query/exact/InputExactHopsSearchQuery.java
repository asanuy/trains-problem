package com.asanuy.trainsproblem.core.application.search.hops.query.exact;

import com.asanuy.trainsproblem.core.application.search.SearchQueryResponse;
import com.asanuy.trainsproblem.shared.Validate;
import com.asanuy.trainsproblem.shared.cqs.Query;

public class InputExactHopsSearchQuery extends Query<SearchQueryResponse> {

  private final String[] graph;
  private final String origin;
  private final String destination;
  private final int hops;

  private InputExactHopsSearchQuery(String[] graph, String origin, String destination, int hops) {
    Validate.isNotNull(graph, "Graph");
    Validate.isNotNull(origin, "Origin vertex");
    Validate.isNotNull(destination, "Destination vertex");
    Validate.isGreaterThanZero(hops, "Hops");
    this.graph = graph;
    this.origin = origin;
    this.destination = destination;
    this.hops = hops;
  }

  public static InputExactHopsSearchQuery of(
      String[] graph, String origin, String destination, int hops) {
    return new InputExactHopsSearchQuery(graph, origin, destination, hops);
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
