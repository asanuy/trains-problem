package com.asanuy.trainsproblem.core.application.search.distance.handler.max;

import com.asanuy.trainsproblem.core.application.search.SearchQueryResponse;
import com.asanuy.trainsproblem.core.application.search.SearchQueryResponseMapper;
import com.asanuy.trainsproblem.core.application.search.distance.query.max.InputMaxDistanceSearchQuery;
import com.asanuy.trainsproblem.core.domain.service.route.RouteService;
import com.asanuy.trainsproblem.core.domain.service.search.SearchQuery;
import com.asanuy.trainsproblem.shared.Validate;
import com.asanuy.trainsproblem.shared.cqs.QueryExecutionHandler;

public class MaxDistanceQueryHandler
    implements QueryExecutionHandler<InputMaxDistanceSearchQuery, SearchQueryResponse> {

  private final RouteService service;

  public MaxDistanceQueryHandler() {
    this.service = new RouteService();
  }

  @Override
  public SearchQueryResponse execute(InputMaxDistanceSearchQuery inputQuery) {
    Validate.isNotNull(inputQuery, "Input max distance query");

    SearchQuery query = MaxDistanceSearchQueryMapper.of(inputQuery);
    return SearchQueryResponseMapper.of(service.searchExistentRoutes(query));
  }
}
