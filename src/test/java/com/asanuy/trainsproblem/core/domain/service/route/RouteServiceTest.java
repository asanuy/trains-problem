package com.asanuy.trainsproblem.core.domain.service.route;

import com.asanuy.trainsproblem.core.application.route.distance.handler.RouteDistanceQueryMapper;
import com.asanuy.trainsproblem.core.application.route.distance.query.InputRouteDistanceQuery;
import com.asanuy.trainsproblem.core.application.route.shortest.handler.ShortestRouteQueryMapper;
import com.asanuy.trainsproblem.core.application.route.shortest.query.InputShortestRouteQuery;
import com.asanuy.trainsproblem.core.application.search.distance.handler.max.MaxDistanceSearchQueryMapper;
import com.asanuy.trainsproblem.core.application.search.distance.query.max.InputMaxDistanceSearchQuery;
import com.asanuy.trainsproblem.core.application.search.hops.handler.exact.ExactHopsSearchQueryMapper;
import com.asanuy.trainsproblem.core.application.search.hops.handler.max.MaxHopsSearchQueryMapper;
import com.asanuy.trainsproblem.core.application.search.hops.query.exact.InputExactHopsSearchQuery;
import com.asanuy.trainsproblem.core.application.search.hops.query.max.InputMaxHopsSearchQuery;
import com.asanuy.trainsproblem.core.domain.service.route.distance.query.RouteDistanceQuery;
import com.asanuy.trainsproblem.core.domain.service.route.shortest.query.ShortestRouteQuery;
import com.asanuy.trainsproblem.core.domain.service.search.SearchQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RouteServiceTest {

  private static final String[] GRAPH = {
    "AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"
  };

  private static final String[] ROUTE1 = {"A", "B", "C"};
  private static final String[] ROUTE2 = {"A", "D"};
  private static final String[] ROUTE3 = {"A", "D", "C"};
  private static final String[] ROUTE4 = {"A", "E", "B", "C", "D"};
  private static final String[] ROUTE5 = {"A", "E", "D"};

  private RouteService routeService;

  @BeforeEach
  public void setUp() {
    routeService = new RouteService();
  }

  @Test
  public void whenCalculateDistanceWithRoute1_thenReturn9() {
    InputRouteDistanceQuery inputQuery = InputRouteDistanceQuery.of(GRAPH, ROUTE1);
    RouteDistanceQuery query = RouteDistanceQueryMapper.of(inputQuery);
    assertEquals(9, routeService.calculateDistance(query).value());
  }

  @Test
  public void whenCalculateDistanceWithRoute2_thenReturn5() {
    InputRouteDistanceQuery inputQuery = InputRouteDistanceQuery.of(GRAPH, ROUTE2);
    RouteDistanceQuery query = RouteDistanceQueryMapper.of(inputQuery);
    assertEquals(5, routeService.calculateDistance(query).value());
  }

  @Test
  public void whenCalculateDistanceWithRoute3_thenReturn13() {
    InputRouteDistanceQuery inputQuery = InputRouteDistanceQuery.of(GRAPH, ROUTE3);
    RouteDistanceQuery query = RouteDistanceQueryMapper.of(inputQuery);
    assertEquals(13, routeService.calculateDistance(query).value());
  }

  @Test
  public void whenCalculateDistanceWithRoute4_thenReturn22() {
    InputRouteDistanceQuery inputQuery = InputRouteDistanceQuery.of(GRAPH, ROUTE4);
    RouteDistanceQuery query = RouteDistanceQueryMapper.of(inputQuery);
    assertEquals(22, routeService.calculateDistance(query).value());
  }

  @Test
  public void whenCalculateDistanceWithRoute5_thenThrowNoSuchRouteExistsException() {
    InputRouteDistanceQuery inputQuery = InputRouteDistanceQuery.of(GRAPH, ROUTE5);
    RouteDistanceQuery query = RouteDistanceQueryMapper.of(inputQuery);
    assertThrows(
        NoSuchRouteExistsException.class, () -> routeService.calculateDistance(query).value());
  }

  @Test
  public void whenSearchExistentRoutesFromCToCWithMax3Hops_thenReturn2() {
    InputMaxHopsSearchQuery inputQuery = InputMaxHopsSearchQuery.of(GRAPH, "C", "C", 3);
    SearchQuery query = MaxHopsSearchQueryMapper.of(inputQuery);
    assertEquals(2, routeService.searchExistentRoutes(query).size());
  }

  @Test
  public void whenSearchExistentRoutesFromAToCWithExactly4Hops_thenReturn3() {
    InputExactHopsSearchQuery inputQuery = InputExactHopsSearchQuery.of(GRAPH, "A", "C", 4);
    SearchQuery query = ExactHopsSearchQueryMapper.of(inputQuery);
    assertEquals(3, routeService.searchExistentRoutes(query).size());
  }

  @Test
  public void whenSearchShortestRouteFromAToC_thenReturn9() {
    InputShortestRouteQuery inputQuery = InputShortestRouteQuery.of(GRAPH, "A", "C");
    ShortestRouteQuery query = ShortestRouteQueryMapper.of(inputQuery);
    assertEquals(9, routeService.searchShortestRoute(query).value());
  }

  @Test
  public void whenSearchShortestRouteFromBToB_thenReturn9() {
    InputShortestRouteQuery inputQuery = InputShortestRouteQuery.of(GRAPH, "B", "B");
    ShortestRouteQuery query = ShortestRouteQueryMapper.of(inputQuery);
    assertEquals(9, routeService.searchShortestRoute(query).value());
  }

  @Test
  public void whenSearchExistentRoutesFromCToCWithMax30Distance_thenReturn7() {
    InputMaxDistanceSearchQuery inputQuery = InputMaxDistanceSearchQuery.of(GRAPH, "C", "C", 30);
    SearchQuery query = MaxDistanceSearchQueryMapper.of(inputQuery);
    assertEquals(7, routeService.searchExistentRoutes(query).size());
  }
}
