package org.acme;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import lombok.Value;

@Value
public class ClassTypeProvider <T> {

  Class<T> tClass;

  TypeReference<T> typeReference;

  JavaType javaType;

  public static <T> ClassTypeProvider<T> of(final TypeReference<T> typeReference) {
    return new ClassTypeProvider<>(typeReference);
  }

  private ClassTypeProvider(final TypeReference<T> typeReference) {

    tClass = null;
    this.typeReference = typeReference;
    javaType = null;
  }

}
