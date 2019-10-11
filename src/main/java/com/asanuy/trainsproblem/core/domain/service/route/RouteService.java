package com.asanuy.trainsproblem.core.domain.service.route;

import com.asanuy.trainsproblem.core.domain.model.Distance;
import com.asanuy.trainsproblem.core.domain.model.graph.Edge;
import com.asanuy.trainsproblem.core.domain.model.graph.Graph;
import com.asanuy.trainsproblem.core.domain.model.graph.Route;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.core.domain.service.route.distance.query.RouteDistanceQuery;
import com.asanuy.trainsproblem.core.domain.service.route.shortest.query.ShortestRouteQuery;
import com.asanuy.trainsproblem.core.domain.service.search.Filter;
import com.asanuy.trainsproblem.core.domain.service.search.SearchQuery;
import com.asanuy.trainsproblem.shared.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class RouteService {

  public Distance calculateDistance(RouteDistanceQuery query) {
    Validate.isNotNull(query, "Distance query");

    Graph graph = query.graph();
    Route route = query.route();

    Distance distance = Distance.zero();
    for (int i = 0; i < route.vertices().size() - 1; i++) {
      Vertex origin = route.vertices().get(i);
      Vertex destination = route.vertices().get(i + 1);
      Edge edge = graph.get(origin, destination);
      if (edge == null) {
        throw NoSuchRouteExistsException.withRoute(route);
      }
      distance.add(edge.weight().value());
    }
    return distance;
  }

  public List<Route> searchExistentRoutes(SearchQuery query) {
    return DFS(query.graph(), query.origin(), query.filter(), Collections.emptyList());
  }

  private List<Route> DFS(Graph graph, Vertex currentVertex, Filter filter, List<Edge> edges) {

    if (filter.stop(edges)) {
      return Collections.emptyList();
    }

    List<Route> routes = new ArrayList<>();
    for (Vertex vertex : graph.getAdjacent(currentVertex)) {
      List<Edge> copyEdges = copy(edges);
      copyEdges.add(graph.get(currentVertex, vertex));
      if (filter.matches(copyEdges)) {
        List<Vertex> vertices = copyEdges.stream().map(Edge::origin).collect(Collectors.toList());
        vertices.add(vertex);
        routes.add(Route.of(vertices));
      }
      routes.addAll(DFS(graph, vertex, filter, copyEdges));
    }
    return routes;
  }

  private List copy(List list) {
    return new ArrayList(list);
  }

  public Distance searchShortestRoute(ShortestRouteQuery query) {
    return dijkstra(query.graph(), query.origin(), query.destination());
  }

  private Distance dijkstra(Graph graph, Vertex origin, Vertex destination) {
    int initialCapacity = graph.getAll().size();
    Map<Vertex, Integer> distances = initDistancesMap(graph, initialCapacity);
    PriorityQueue<Pair<Integer, Vertex>> queue =
        new PriorityQueue<>(initialCapacity, Comparator.comparingInt(Pair::key));
    Set<Vertex> visited = new HashSet<>(initialCapacity);

    distances.put(origin, 0);
    queue.offer(new Pair(0, origin));

    while (!queue.isEmpty()) {
      Pair<Integer, Vertex> pair = queue.poll();
      if (!visited.contains(pair.value)) {
        visited.add(pair.value);

        for (Vertex vertex : graph.getAdjacent(pair.value)) {
          if (!visited.contains(vertex) || destination.equals(vertex)) {
            Edge edge = graph.get(pair.value, vertex);
            Integer newDistance = Integer.sum(distances.get(pair.value), edge.weight().value());
            Integer currentDistance = distances.get(vertex);
            if (currentDistance.compareTo(newDistance) > 0 || currentDistance.compareTo(0) == 0) {
              queue.offer(new Pair<>(newDistance, vertex));
              distances.put(vertex, newDistance);
            }
          }
        }
      }
    }

    return Distance.of(distances.get(destination));
  }

  private Map<Vertex, Integer> initDistancesMap(Graph graph, int initialCapacity) {
    Map<Vertex, Integer> distances = new HashMap<>(initialCapacity);

    for (Vertex vertex : graph.getAll()) {
      distances.put(vertex, Integer.MAX_VALUE);
    }
    return distances;
  }

  private class Pair<K, V> {
    private K key;
    private V value;

    private Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K key() {
      return key;
    }

    public V value() {
      return value;
    }
  }
}
