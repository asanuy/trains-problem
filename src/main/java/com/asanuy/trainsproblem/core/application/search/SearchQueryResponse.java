package com.asanuy.trainsproblem.core.application.search;

import com.asanuy.trainsproblem.shared.Validate;

public class SearchQueryResponse {

  private final Integer routes;

  private SearchQueryResponse(Integer routes) {
    this.routes = routes;
  }

  public static SearchQueryResponse valueOf(Integer routes) {
    Validate.isNotNull(routes, "Routes");
    return new SearchQueryResponse(routes);
  }

  @Override
  public String toString() {
    return routes.toString();
  }
}
