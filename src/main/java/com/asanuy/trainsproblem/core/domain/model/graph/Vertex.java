package com.asanuy.trainsproblem.core.domain.model.graph;

import com.asanuy.trainsproblem.shared.Validate;

import java.util.Objects;

public class Vertex {

  private final String value;

  private Vertex(String value) {
    Validate.isNotBlank(value, "Vertex value");
    this.value = value;
  }

  public static Vertex valueOf(Character c) {
    return new Vertex(Character.toString(c));
  }

  public static Vertex valueOf(String value) {
    return new Vertex(value);
  }

  public String value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vertex vertex = (Vertex) o;
    return value.equals(vertex.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return value;
  }
}
