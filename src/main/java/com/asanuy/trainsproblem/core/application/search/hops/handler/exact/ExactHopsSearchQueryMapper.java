package com.asanuy.trainsproblem.core.application.search.hops.handler.exact;

import com.asanuy.trainsproblem.core.application.DirectedGraphMapper;
import com.asanuy.trainsproblem.core.application.search.hops.query.exact.InputExactHopsSearchQuery;
import com.asanuy.trainsproblem.core.domain.model.graph.Graph;
import com.asanuy.trainsproblem.core.domain.model.graph.Vertex;
import com.asanuy.trainsproblem.core.domain.service.search.Filter;
import com.asanuy.trainsproblem.core.domain.service.search.SearchQuery;
import com.asanuy.trainsproblem.core.domain.service.search.hops.filter.exact.ExactHopsFilter;
import com.asanuy.trainsproblem.shared.Validate;

public class ExactHopsSearchQueryMapper {

  public static SearchQuery of(InputExactHopsSearchQuery query) {
    Validate.isNotNull(query, "Input exact hops query");

    Graph graph = DirectedGraphMapper.of(query.graph());

    Filter filter = ExactHopsFilter.of(Vertex.valueOf(query.destination()), query.hops());
    return SearchQuery.of(graph, Vertex.valueOf(query.origin()), filter);
  }
}
