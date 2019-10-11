package com.asanuy.trainsproblem.core.application.search.hops.handler.max;

import com.asanuy.trainsproblem.core.application.DirectedGraphMapper;
import com.asanuy.trainsproblem.core.application.search.hops.query.max.InputMaxHopsSearchQuery;
import com.asanuy.trainsproblem.core.domain.model.graph.Graph;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.core.domain.service.search.Filter;
import com.asanuy.trainsproblem.core.domain.service.search.SearchQuery;
import com.asanuy.trainsproblem.core.domain.service.search.hops.filter.max.MaxHopsFilter;
import com.asanuy.trainsproblem.shared.Validate;

public class MaxHopsSearchQueryMapper {

  public static SearchQuery of(InputMaxHopsSearchQuery query) {
    Validate.isNotNull(query, "Input max hops query");

    Graph graph = DirectedGraphMapper.of(query.graph());

    Filter filter = MaxHopsFilter.of(Vertex.valueOf(query.destination()), query.hops());
    return SearchQuery.of(graph, Vertex.valueOf(query.origin()), filter);
  }
}
