package com.asanuy.trainsproblem.shared.cqs;

public interface QueryExecutionHandler<T extends Query<S>, S> {

  S execute(T query);
}
