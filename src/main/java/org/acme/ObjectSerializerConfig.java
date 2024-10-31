package org.acme;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ObjectSerializerConfig {

  final ObjectMapper objectMapper;

  @Singleton
  @Produces
  public ObjectSerializer objectSerializer() {
    return createObjectSerializer(objectMapper);
  }

  private ObjectSerializer createObjectSerializer(final ObjectMapper objectMapper) {
    return new ObjectSerializerImpl(objectMapper);
  }
}
