package com.asanuy.trainsproblem.core.application.search.hops.handler.exact;

import com.asanuy.trainsproblem.core.application.search.SearchQueryResponse;
import com.asanuy.trainsproblem.core.application.search.SearchQueryResponseMapper;
import com.asanuy.trainsproblem.core.application.search.hops.query.exact.InputExactHopsSearchQuery;
import com.asanuy.trainsproblem.core.domain.service.route.RouteService;
import com.asanuy.trainsproblem.core.domain.service.search.SearchQuery;
import com.asanuy.trainsproblem.shared.Validate;
import com.asanuy.trainsproblem.shared.cqs.QueryExecutionHandler;

public class ExactHopsQueryHandler
    implements QueryExecutionHandler<InputExactHopsSearchQuery, SearchQueryResponse> {

  private final RouteService service;

  public ExactHopsQueryHandler() {
    this.service = new RouteService();
  }

  @Override
  public SearchQueryResponse execute(InputExactHopsSearchQuery inputQuery) {
    Validate.isNotNull(inputQuery, "Input exact hops search query");

    SearchQuery query = ExactHopsSearchQueryMapper.of(inputQuery);
    return SearchQueryResponseMapper.of(service.searchExistentRoutes(query));
  }
}
