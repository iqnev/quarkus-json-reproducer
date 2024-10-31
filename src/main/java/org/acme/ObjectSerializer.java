package org.acme;

public interface ObjectSerializer {
  String toString(Object object);

  <T> T toObject(String json, ClassTypeProvider<T> classTypeProvider);
}
