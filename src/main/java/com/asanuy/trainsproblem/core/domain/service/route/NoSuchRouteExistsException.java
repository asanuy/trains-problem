package com.asanuy.trainsproblem.core.domain.service.route;

import com.asanuy.trainsproblem.core.domain.model.graph.Route;

public final class NoSuchRouteExistsException extends RuntimeException {

  private NoSuchRouteExistsException(String route) {
    super(String.format("No such route exists: %s", route));
  }

  public static NoSuchRouteExistsException withRoute(Route route) {
    return new NoSuchRouteExistsException(route.toString());
  }
}
