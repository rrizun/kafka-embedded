package io.example;

import java.util.*;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.gson.*;

/**
 * LogHelper
 */
public class LogHelper {
  private final Object object;

  public LogHelper(Object object) {
    this.object = object;
  }

  public void log(Object... args) {
    List<Object> parts = Lists.newArrayList();
    parts.add(new Date());
    parts.add(object);
    for (Object arg : args)
      parts.add(render(arg));
    System.out.println(Joiner.on(" ").useForNull("null").join(parts));
  }

  private Object render(Object arg) {

    // is it a class?
    if (arg instanceof Class)
      return arg; // yes

    // is it an error?
    if (arg instanceof Throwable)
      return Throwables.getStackTraceAsString((Throwable) arg); // yes

    JsonElement jsonElement = new Gson().toJsonTree(arg);
    // is it an array?
    if (jsonElement.isJsonArray())
      return jsonElement; // yes
    // is it an object?
    if (jsonElement.isJsonObject())
      return jsonElement; // yes

    // default
    return arg;

  }
}