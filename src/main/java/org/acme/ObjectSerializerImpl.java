package org.acme;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record ObjectSerializerImpl(ObjectMapper objectMapper) implements ObjectSerializer {

  @Override
  public String toString(final Object object) {

    if (object == null) {
      return "";
    }

    if (object instanceof final CharSequence string) {
      return string.toString();
    }

    String json = null;
    try {
      json = objectMapper.writeValueAsString(object);
    } catch (final JacksonException e) {
      log.error("Failed to write object to json, error: {}", e.getMessage(), e);
    }

    return json;
  }

  @Override
  public <T> T toObject(final String json, final ClassTypeProvider<T> classTypeProvider) {

    T object = null;

    try {
      object = toObjectInternal(json, classTypeProvider);
    } catch (final JacksonException e) {
      log.error("Failed to read json, error: {}, json: {}", e.getMessage(), json, e);
    }

    return object;
  }

  private <T> T toObjectInternal(final String json, final ClassTypeProvider<T> classTypeProvider)
      throws JacksonException {

    final T object;
    if (classTypeProvider.getTClass() != null) {
      if (classTypeProvider.getTClass().equals(String.class)) {
        return (T) json;
      }

      object = objectMapper.readValue(json, classTypeProvider.getTClass());
    } else if (classTypeProvider.getTypeReference() != null) {
      if (classTypeProvider
          .getTypeReference()
          .getType()
          .getTypeName()
          .equals(String.class.getTypeName())) {
        return (T) json;
      }

      object = objectMapper.readValue(json, classTypeProvider.getTypeReference());
    } else if (classTypeProvider.getJavaType() != null) {
      if (classTypeProvider.getJavaType().getRawClass().equals(String.class)) {
        return (T) json;
      }

      object = objectMapper.readValue(json, classTypeProvider.getJavaType());
    } else {
      throw new IllegalArgumentException("Missing class type");
    }

    return object;
  }
}
