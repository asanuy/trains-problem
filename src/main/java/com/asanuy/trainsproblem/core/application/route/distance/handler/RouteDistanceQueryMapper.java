package com.asanuy.trainsproblem.core.application.route.distance.handler;

import com.asanuy.trainsproblem.core.application.DirectedGraphMapper;
import com.asanuy.trainsproblem.core.application.route.distance.query.InputRouteDistanceQuery;
import com.asanuy.trainsproblem.core.domain.model.graph.Graph;
import com.asanuy.trainsproblem.core.domain.model.graph.Route;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.core.domain.service.route.distance.query.RouteDistanceQuery;
import com.asanuy.trainsproblem.shared.Validate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteDistanceQueryMapper {

  public static RouteDistanceQuery of(InputRouteDistanceQuery query) {
    Validate.isNotNull(query, "Input distance query");

    Graph graph = DirectedGraphMapper.of(query.graph());

    List<Vertex> vertices =
        Stream.of(query.route()).map(Vertex::valueOf).collect(Collectors.toList());

    return RouteDistanceQuery.of(graph, Route.of(vertices));
  }
}
