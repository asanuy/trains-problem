package com.asanuy.trainsproblem.core.domain.service.search.hops.filter.max;

import com.asanuy.trainsproblem.core.domain.model.graph.Edge;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.core.domain.service.search.Filter;
import com.asanuy.trainsproblem.shared.Validate;

import java.util.List;

public class MaxHopsFilter implements Filter {

  private Vertex destination;
  private int hops;

  private MaxHopsFilter(Vertex destination, int hops) {
    Validate.isNotNull(destination, "Destination vertex");
    Validate.isGreaterThanZero(hops, "Hops");
    this.destination = destination;
    this.hops = hops;
  }

  public static MaxHopsFilter of(Vertex destination, int hops) {
    return new MaxHopsFilter(destination, hops);
  }

  @Override
  public boolean stop(List<Edge> edges) {
    return edges.size() == hops;
  }

  @Override
  public boolean matches(List<Edge> edges) {
    int size = edges.size();
    return destination.equals(edges.get(size - 1).destination());
  }
}
