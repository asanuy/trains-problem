package com.asanuy.trainsproblem.core.application.route.shortest.handler;

import com.asanuy.trainsproblem.core.application.route.shortest.query.InputShortestRouteQuery;
import com.asanuy.trainsproblem.core.application.route.shortest.query.ShortestRouteQueryResponse;
import com.asanuy.trainsproblem.core.application.route.shortest.query.ShortestRouteQueryResponseMapper;
import com.asanuy.trainsproblem.core.domain.service.route.RouteService;
import com.asanuy.trainsproblem.core.domain.service.route.shortest.query.ShortestRouteQuery;
import com.asanuy.trainsproblem.shared.Validate;
import com.asanuy.trainsproblem.shared.cqs.QueryExecutionHandler;

public class ShortestRouteQueryHandler
    implements QueryExecutionHandler<InputShortestRouteQuery, ShortestRouteQueryResponse> {

  private RouteService service;

  public ShortestRouteQueryHandler() {
    this.service = new RouteService();
  }

  @Override
  public ShortestRouteQueryResponse execute(InputShortestRouteQuery inputQuery) {
    Validate.isNotNull(inputQuery, "Input shortest route query");

    ShortestRouteQuery query = ShortestRouteQueryMapper.of(inputQuery);
    return ShortestRouteQueryResponseMapper.of(service.searchShortestRoute(query));
  }
}
