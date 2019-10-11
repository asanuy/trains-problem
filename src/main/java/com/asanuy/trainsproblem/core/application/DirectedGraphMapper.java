package com.asanuy.trainsproblem.core.application;

import com.asanuy.trainsproblem.core.domain.model.graph.Edge;
import com.asanuy.trainsproblem.core.domain.model.graph.Graph;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.core.domain.model.graph.Weight;
import com.asanuy.trainsproblem.core.domain.model.graph.directed.DirectedGraph;
import com.asanuy.trainsproblem.shared.Validate;

public class DirectedGraphMapper {

  private static final int ORIGIN = 0;
  private static final int DESTINATION = 1;
  private static final int WEIGHT = 2;

  public static Graph of(String[] graph) {
    Validate.isNotNull(graph, "Graph");

    Graph directedGraph = new DirectedGraph();
    for (String edge : graph) {
      Vertex origin = Vertex.valueOf(edge.trim().charAt(ORIGIN));
      Vertex destination = Vertex.valueOf(edge.trim().charAt(DESTINATION));
      Weight weight = Weight.valueOf(edge.trim().charAt(WEIGHT));

      directedGraph.add(Edge.of(origin, destination, weight));
    }
    return directedGraph;
  }
}
