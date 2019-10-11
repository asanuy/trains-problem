package com.asanuy.trainsproblem.core.domain.service.search.distance.filter.max;

import com.asanuy.trainsproblem.core.domain.model.graph.Edge;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.core.domain.model.graph.Weight;
import com.asanuy.trainsproblem.core.domain.service.search.Filter;
import com.asanuy.trainsproblem.shared.Validate;

import java.util.List;

public class MaxDistanceFilter implements Filter {

  private Vertex destination;
  private int distance;

  private MaxDistanceFilter(Vertex destination, int distance) {
    Validate.isNotNull(destination, "Destination vertex");
    Validate.isGreaterThanZero(distance, "Max distance");
    this.destination = destination;
    this.distance = distance;
  }

  public static MaxDistanceFilter of(Vertex destination, int distance) {
    return new MaxDistanceFilter(destination, distance);
  }

  private boolean isValid(List<Edge> edges) {
    return distance > edges.stream().map(Edge::weight).mapToInt(Weight::value).sum();
  }

  @Override
  public boolean stop(List<Edge> edges) {
    return !isValid(edges);
  }

  @Override
  public boolean matches(List<Edge> edges) {
    int size = edges.size();
    return destination.equals(edges.get(size - 1).destination()) && isValid(edges);
  }
}
