package com.asanuy.trainsproblem.core.application.search.distance.handler.max;

import com.asanuy.trainsproblem.core.application.DirectedGraphMapper;
import com.asanuy.trainsproblem.core.application.search.distance.query.max.InputMaxDistanceSearchQuery;
import com.asanuy.trainsproblem.core.domain.model.graph.Graph;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.core.domain.service.search.Filter;
import com.asanuy.trainsproblem.core.domain.service.search.SearchQuery;
import com.asanuy.trainsproblem.core.domain.service.search.distance.filter.max.MaxDistanceFilter;
import com.asanuy.trainsproblem.shared.Validate;

public class MaxDistanceSearchQueryMapper {

  public static SearchQuery of(InputMaxDistanceSearchQuery query) {
    Validate.isNotNull(query, "Input max distance query");

    Graph graph = DirectedGraphMapper.of(query.graph());

    Filter filter = MaxDistanceFilter.of(Vertex.valueOf(query.destination()), query.distance());
    return SearchQuery.of(graph, Vertex.valueOf(query.origin()), filter);
  }
}
