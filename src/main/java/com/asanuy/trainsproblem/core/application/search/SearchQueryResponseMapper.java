package com.asanuy.trainsproblem.core.application.search;

import com.asanuy.trainsproblem.core.domain.model.graph.Route;
import com.asanuy.trainsproblem.shared.Validate;

import java.util.List;

public class SearchQueryResponseMapper {

  public static SearchQueryResponse of(List<Route> routes) {
    Validate.isNotNull(routes, "Routes");
    return SearchQueryResponse.valueOf(routes.size());
  }
}
