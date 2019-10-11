package com.asanuy.trainsproblem.core.domain.model.graph.directed;

import com.asanuy.trainsproblem.core.domain.model.graph.Edge;
import com.asanuy.trainsproblem.core.domain.model.graph.Graph;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class DirectedGraph implements Graph {

  private final Map<Vertex, Map<Vertex, Edge>> directedGraph;
  private final Set<Vertex> vertices;

  public DirectedGraph() {
    directedGraph = new HashMap<>();
    vertices = new HashSet<>();
  }

  private Map<Vertex, Edge> getMapFromOrigin(Vertex origin) {
    // Origin vertex may not exist
    return Optional.ofNullable(directedGraph.get(origin)).orElseGet(HashMap::new);
  }

  @Override
  public void add(Edge edge) {
    Map<Vertex, Edge> destinationMap = getMapFromOrigin(edge.origin());
    destinationMap.put(edge.destination(), edge);
    directedGraph.put(edge.origin(), destinationMap);
    vertices.add(edge.origin());
    vertices.add(edge.origin());
  }

  @Override
  public Edge get(Vertex origin, Vertex destination) {
    Map<Vertex, Edge> destinationMap = getMapFromOrigin(origin);
    return destinationMap.get(destination);
  }

  @Override
  public List<Vertex> getAdjacent(Vertex origin) {
    return new ArrayList<>(getMapFromOrigin(origin).keySet());
  }

  @Override
  public Set<Vertex> getAll() {
    return vertices;
  }
}
