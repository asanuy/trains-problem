package com.asanuy.trainsproblem.core.domain.model.graph;

import java.util.List;
import java.util.Set;

public interface Graph {

  void add(Edge edge);

  Edge get(Vertex origin, Vertex destination);

  List<Vertex> getAdjacent(Vertex origin);

  Set<Vertex> getAll();
}
