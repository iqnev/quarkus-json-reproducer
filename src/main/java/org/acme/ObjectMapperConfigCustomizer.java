package org.acme;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.jackson.ObjectMapperCustomizer;
import jakarta.inject.Singleton;

@Singleton
public class ObjectMapperConfigCustomizer implements ObjectMapperCustomizer {

  @Override
  public int priority() {
    return MINIMUM_PRIORITY;
  }

  @Override
  public void customize(final ObjectMapper mapper) {

    mapper.registerModule(new JavaTimeModule());
    mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(FAIL_ON_EMPTY_BEANS, false);

    final var encoder = new SimpleModule();
    mapper.registerModule(encoder);
  }
}
