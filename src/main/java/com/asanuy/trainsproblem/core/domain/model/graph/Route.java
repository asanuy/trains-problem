package com.asanuy.trainsproblem.core.domain.model.graph;

import com.asanuy.trainsproblem.shared.Validate;

import java.util.List;

public class Route {

  private final List<Vertex> vertices;

  private Route(List<Vertex> vertices) {
    Validate.isNotNull(vertices, "Vertices");
    this.vertices = vertices;
  }

  public static Route of(List<Vertex> vertices) {
    return new Route(vertices);
  }

  public List<Vertex> vertices() {
    return vertices;
  }

  @Override
  public String toString() {
    return vertices.toString();
  }
}
