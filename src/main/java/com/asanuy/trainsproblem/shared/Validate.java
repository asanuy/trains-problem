package com.asanuy.trainsproblem.shared;

public class Validate {

  private static void throwException(String name, String operation) {
    throw new IllegalStateException(String.format("'%s' %s", name, operation));
  }

  private static boolean equalsToBlank(String value) {
    return value == null || value.trim().length() == 0;
  }

  public static void isNotBlank(String value, String name) {
    if (equalsToBlank(value)) {
      throwException(name, "is blank");
    }
  }

  public static void isNotNull(Object value, String name) {
    if (value == null) {
      throwException(name, "is null");
    }
  }

  public static void isGreaterThanZero(Integer value, String name) {
    isNotNull(value, name);
    if (Integer.valueOf(0).compareTo(value) >= 0) {
      throwException(name, "is not greater than zero");
    }
  }
}
