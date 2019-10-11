package com.asanuy.trainsproblem.core.infrastructure;

import com.asanuy.trainsproblem.core.application.route.distance.handler.RouteDistanceQueryHandler;
import com.asanuy.trainsproblem.core.application.route.distance.query.InputRouteDistanceQuery;
import com.asanuy.trainsproblem.core.application.route.distance.query.RouteDistanceQueryResponse;
import com.asanuy.trainsproblem.core.application.route.shortest.handler.ShortestRouteQueryHandler;
import com.asanuy.trainsproblem.core.application.route.shortest.query.InputShortestRouteQuery;
import com.asanuy.trainsproblem.core.application.route.shortest.query.ShortestRouteQueryResponse;
import com.asanuy.trainsproblem.core.application.search.SearchQueryResponse;
import com.asanuy.trainsproblem.core.application.search.distance.handler.max.MaxDistanceQueryHandler;
import com.asanuy.trainsproblem.core.application.search.distance.query.max.InputMaxDistanceSearchQuery;
import com.asanuy.trainsproblem.core.application.search.hops.handler.exact.ExactHopsQueryHandler;
import com.asanuy.trainsproblem.core.application.search.hops.handler.max.MaxHopsQueryHandler;
import com.asanuy.trainsproblem.core.application.search.hops.query.exact.InputExactHopsSearchQuery;
import com.asanuy.trainsproblem.core.application.search.hops.query.max.InputMaxHopsSearchQuery;
import com.asanuy.trainsproblem.shared.cqs.QueryExecutionHandler;

import java.util.Scanner;
import java.util.regex.Pattern;

public class CommandLineScanner {

  // Matches patterns such as 'AB5, CD8, EF2 ...' where spaces are optional
  private static final Pattern PATTERN = Pattern.compile("[a-zA-Z]{2}\\d(,\\s?[a-zA-Z]{2}\\d)+");

  private static final String[] ROUTE1 = {"A", "B", "C"};
  private static final String[] ROUTE2 = {"A", "D"};
  private static final String[] ROUTE3 = {"A", "D", "C"};
  private static final String[] ROUTE4 = {"A", "E", "B", "C", "D"};
  private static final String[] ROUTE5 = {"A", "E", "D"};

  public static void main(String[] args) {
    QueryExecutionHandler<InputRouteDistanceQuery, RouteDistanceQueryResponse>
        distanceQueryHandler = new RouteDistanceQueryHandler();
    QueryExecutionHandler<InputMaxHopsSearchQuery, SearchQueryResponse> maxHopsQueryHandler =
        new MaxHopsQueryHandler();
    QueryExecutionHandler<InputExactHopsSearchQuery, SearchQueryResponse> exactHopsQueryHandler =
        new ExactHopsQueryHandler();
    QueryExecutionHandler<InputShortestRouteQuery, ShortestRouteQueryResponse>
        shortestRouteQueryHandler = new ShortestRouteQueryHandler();
    QueryExecutionHandler<InputMaxDistanceSearchQuery, SearchQueryResponse>
        maxDistanceQueryHandler = new MaxDistanceQueryHandler();

    System.out.println("Enter a graph following the format agreed");

    try (Scanner scanner = new Scanner(System.in)) {
      while (scanner.hasNext()) {

        String line = scanner.nextLine();
        validateInput(line);
        String[] lineArray = line.split(",");

        // Calculate distances for all available routes
        System.out.println(calculateDistanceRoute1(distanceQueryHandler, lineArray));
        System.out.println(calculateDistanceRoute2(distanceQueryHandler, lineArray));
        System.out.println(calculateDistanceRoute3(distanceQueryHandler, lineArray));
        System.out.println(calculateDistanceRoute4(distanceQueryHandler, lineArray));
        calculateDistanceRoute5(distanceQueryHandler, lineArray);

        // Search routes filtering by hops
        System.out.println(
            searchExistentRoutesFromCToCWithMax3Hops(maxHopsQueryHandler, lineArray));
        System.out.println(
            searchExistentRoutesFromAToCWithExactly4Hops(exactHopsQueryHandler, lineArray));

        // Calculate shortest route
        System.out.println(searchShortestRouteFromAToC(shortestRouteQueryHandler, lineArray));
        System.out.println(searchShortestRouteFromBToB(shortestRouteQueryHandler, lineArray));

        // Search routes filtering by distance
        System.out.println(
            searchExistentRoutesFromCToCWithMax30Distance(maxDistanceQueryHandler, lineArray));
      }
    }
  }

  private static void validateInput(String line) {
    if (!PATTERN.matcher(line).matches()) {
      throw new IllegalArgumentException("Input provided is not valid");
    }
  }

  private static RouteDistanceQueryResponse executeQueryForRoute(
      QueryExecutionHandler<InputRouteDistanceQuery, RouteDistanceQueryResponse> executionHandler,
      String[] lineArray,
      String[] route) {
    return executionHandler.execute(InputRouteDistanceQuery.of(lineArray, route));
  }

  private static RouteDistanceQueryResponse calculateDistanceRoute1(
      QueryExecutionHandler<InputRouteDistanceQuery, RouteDistanceQueryResponse> executionHandler,
      String[] lineArray) {
    return executeQueryForRoute(executionHandler, lineArray, ROUTE1);
  }

  private static RouteDistanceQueryResponse calculateDistanceRoute2(
      QueryExecutionHandler<InputRouteDistanceQuery, RouteDistanceQueryResponse> executionHandler,
      String[] lineArray) {
    return executeQueryForRoute(executionHandler, lineArray, ROUTE2);
  }

  private static RouteDistanceQueryResponse calculateDistanceRoute3(
      QueryExecutionHandler<InputRouteDistanceQuery, RouteDistanceQueryResponse> executionHandler,
      String[] lineArray) {
    return executeQueryForRoute(executionHandler, lineArray, ROUTE3);
  }

  private static RouteDistanceQueryResponse calculateDistanceRoute4(
      QueryExecutionHandler<InputRouteDistanceQuery, RouteDistanceQueryResponse> executionHandler,
      String[] lineArray) {
    return executeQueryForRoute(executionHandler, lineArray, ROUTE4);
  }

  private static RouteDistanceQueryResponse calculateDistanceRoute5(
      QueryExecutionHandler<InputRouteDistanceQuery, RouteDistanceQueryResponse> executionHandler,
      String[] lineArray) {
    return executeQueryForRoute(executionHandler, lineArray, ROUTE5);
  }

  private static SearchQueryResponse searchExistentRoutesFromCToCWithMax3Hops(
      QueryExecutionHandler<InputMaxHopsSearchQuery, SearchQueryResponse> maxHopsQueryHandler,
      String[] lineArray) {
    return maxHopsQueryHandler.execute(InputMaxHopsSearchQuery.of(lineArray, "C", "C", 3));
  }

  private static SearchQueryResponse searchExistentRoutesFromAToCWithExactly4Hops(
      QueryExecutionHandler<InputExactHopsSearchQuery, SearchQueryResponse> exactHopsQueryHandler,
      String[] lineArray) {
    return exactHopsQueryHandler.execute(InputExactHopsSearchQuery.of(lineArray, "A", "C", 4));
  }

  private static ShortestRouteQueryResponse searchShortestRouteFromAToC(
      QueryExecutionHandler<InputShortestRouteQuery, ShortestRouteQueryResponse>
          shortestRouteQueryHandler,
      String[] lineArray) {
    return shortestRouteQueryHandler.execute(InputShortestRouteQuery.of(lineArray, "A", "C"));
  }

  private static ShortestRouteQueryResponse searchShortestRouteFromBToB(
      QueryExecutionHandler<InputShortestRouteQuery, ShortestRouteQueryResponse>
          shortestRouteQueryHandler,
      String[] lineArray) {
    return shortestRouteQueryHandler.execute(InputShortestRouteQuery.of(lineArray, "B", "B"));
  }

  private static SearchQueryResponse searchExistentRoutesFromCToCWithMax30Distance(
      QueryExecutionHandler<InputMaxDistanceSearchQuery, SearchQueryResponse>
          maxDistanceQueryHandler,
      String[] lineArray) {
    return maxDistanceQueryHandler.execute(InputMaxDistanceSearchQuery.of(lineArray, "C", "C", 30));
  }
}
