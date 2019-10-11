package com.asanuy.trainsproblem.core.domain.service.route.shortest.query;

import com.asanuy.trainsproblem.core.domain.model.graph.Graph;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.shared.Validate;

public class ShortestRouteQuery {

  private final Graph graph;
  private final Vertex origin;
  private final Vertex destination;

  private ShortestRouteQuery(Graph graph, Vertex origin, Vertex destination) {
    Validate.isNotNull(graph, "Graph");
    Validate.isNotNull(origin, "Origin vertex");
    Validate.isNotNull(destination, "Destination vertex");
    this.graph = graph;
    this.origin = origin;
    this.destination = destination;
  }

  public static ShortestRouteQuery of(Graph graph, Vertex origin, Vertex destination) {
    return new ShortestRouteQuery(graph, origin, destination);
  }

  public Graph graph() {
    return graph;
  }

  public Vertex origin() {
    return origin;
  }

  public Vertex destination() {
    return destination;
  }
}
