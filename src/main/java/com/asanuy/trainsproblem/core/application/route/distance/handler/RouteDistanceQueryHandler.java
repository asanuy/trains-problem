package com.asanuy.trainsproblem.core.application.route.distance.handler;

import com.asanuy.trainsproblem.core.application.route.distance.query.InputRouteDistanceQuery;
import com.asanuy.trainsproblem.core.application.route.distance.query.RouteDistanceQueryResponse;
import com.asanuy.trainsproblem.core.application.route.distance.query.RouteDistanceQueryResponseMapper;
import com.asanuy.trainsproblem.core.domain.service.route.NoSuchRouteExistsException;
import com.asanuy.trainsproblem.core.domain.service.route.RouteService;
import com.asanuy.trainsproblem.core.domain.service.route.distance.query.RouteDistanceQuery;
import com.asanuy.trainsproblem.shared.Validate;
import com.asanuy.trainsproblem.shared.cqs.QueryExecutionHandler;

public class RouteDistanceQueryHandler
    implements QueryExecutionHandler<InputRouteDistanceQuery, RouteDistanceQueryResponse> {

  private final RouteService service;

  public RouteDistanceQueryHandler() {
    this.service = new RouteService();
  }

  @Override
  public RouteDistanceQueryResponse execute(InputRouteDistanceQuery inputQuery) {
    Validate.isNotNull(inputQuery, "Input distance query");

    try {
      RouteDistanceQuery query = RouteDistanceQueryMapper.of(inputQuery);
      return RouteDistanceQueryResponseMapper.of(service.calculateDistance(query));
    } catch (NoSuchRouteExistsException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
}
