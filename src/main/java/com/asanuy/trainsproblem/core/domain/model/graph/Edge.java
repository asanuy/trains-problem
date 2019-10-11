package com.asanuy.trainsproblem.core.domain.model.graph;

import com.asanuy.trainsproblem.shared.Validate;

import java.util.Objects;

public class Edge {

  private final Vertex origin;
  private final Vertex destination;
  private final Weight weight;

  private Edge(Vertex origin, Vertex destination, Weight weight) {
    Validate.isNotNull(origin, "Origin vertex");
    Validate.isNotNull(destination, "Destination vertex");
    Validate.isNotNull(weight, "Weight");
    this.origin = origin;
    this.destination = destination;
    this.weight = weight;
  }

  public static Edge of(Vertex origin, Vertex destination, Weight weight) {
    return new Edge(origin, destination, weight);
  }

  public Vertex origin() {
    return origin;
  }

  public Vertex destination() {
    return destination;
  }

  public Weight weight() {
    return weight;
  }

  @Override
  /** Two Edges are considered equals only if their Origin and Destination Vertices are the same */
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Edge edge = (Edge) o;
    return origin.equals(edge.origin) && destination.equals(edge.destination);
  }

  @Override
  /**
   * Two Edges have the same hash code only if their Origin and Destination Vertices are the same
   */
  public int hashCode() {
    return Objects.hash(origin, destination);
  }
}
