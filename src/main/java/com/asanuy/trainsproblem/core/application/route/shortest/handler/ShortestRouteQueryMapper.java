package com.asanuy.trainsproblem.core.application.route.shortest.handler;

import com.asanuy.trainsproblem.core.application.DirectedGraphMapper;
import com.asanuy.trainsproblem.core.application.route.shortest.query.InputShortestRouteQuery;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.core.domain.service.route.shortest.query.ShortestRouteQuery;

public class ShortestRouteQueryMapper {

  public static ShortestRouteQuery of(InputShortestRouteQuery query) {
    return ShortestRouteQuery.of(
        DirectedGraphMapper.of(query.graph()),
        Vertex.valueOf(query.origin()),
        Vertex.valueOf(query.destination()));
  }
}
