package com.asanuy.trainsproblem.core.application.search.hops.handler.max;

import com.asanuy.trainsproblem.core.application.search.SearchQueryResponse;
import com.asanuy.trainsproblem.core.application.search.SearchQueryResponseMapper;
import com.asanuy.trainsproblem.core.application.search.hops.query.max.InputMaxHopsSearchQuery;
import com.asanuy.trainsproblem.core.domain.service.route.RouteService;
import com.asanuy.trainsproblem.core.domain.service.search.SearchQuery;
import com.asanuy.trainsproblem.shared.Validate;
import com.asanuy.trainsproblem.shared.cqs.QueryExecutionHandler;

public class MaxHopsQueryHandler
    implements QueryExecutionHandler<InputMaxHopsSearchQuery, SearchQueryResponse> {

  private final RouteService service;

  public MaxHopsQueryHandler() {
    this.service = new RouteService();
  }

  @Override
  public SearchQueryResponse execute(InputMaxHopsSearchQuery inputQuery) {
    Validate.isNotNull(inputQuery, "Input max hops search query");

    SearchQuery query = MaxHopsSearchQueryMapper.of(inputQuery);
    return SearchQueryResponseMapper.of(service.searchExistentRoutes(query));
  }
}
