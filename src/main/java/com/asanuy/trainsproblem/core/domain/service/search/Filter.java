package com.asanuy.trainsproblem.core.domain.service.search;

import com.asanuy.trainsproblem.core.domain.model.graph.Edge;

import java.util.List;

public interface Filter {

  boolean stop(List<Edge> edges);

  boolean matches(List<Edge> edges);
}
