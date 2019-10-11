package com.asanuy.trainsproblem.core.domain.service.search;

import com.asanuy.trainsproblem.core.domain.model.graph.Graph;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.shared.Validate;

public class SearchQuery {

  private final Graph graph;
  private final Vertex origin;
  private final Filter filter;

  private SearchQuery(Graph graph, Vertex origin, Filter filter) {
    Validate.isNotNull(graph, "Graph");
    Validate.isNotNull(origin, "Origin vertex");
    Validate.isNotNull(filter, "Filter");
    this.graph = graph;
    this.origin = origin;
    this.filter = filter;
  }

  public static SearchQuery of(Graph graph, Vertex origin, Filter filter) {
    return new SearchQuery(graph, origin, filter);
  }

  public Graph graph() {
    return graph;
  }

  public Vertex origin() {
    return origin;
  }

  public Filter filter() {
    return filter;
  }
}
