package org.acme;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/hello")
public class GreetingResource {

  @Inject
  ObjectMapper objectMapper;

  @Inject ObjectSerializer objectSerializer;
  @GET
  public List<StateInfoDto> getState() {

    final String statesJson = "[\n"
        + "  {\n"
        + "    \"name\": \"Alabama\",\n"
        + "    \"code\": \"AL\",\n"
        + "    \"is_enabled\": true\n"
        + "  },\n"
        + "  {\n"
        + "    \"name\": \"Alaska\",\n"
        + "    \"code\": \"AK\",\n"
        + "    \"is_enabled\": true\n"
        + "  },\n"
        + "  {\n"
        + "    \"name\": \"Arizona\",\n"
        + "    \"code\": \"AZ\",\n"
        + "    \"is_enabled\": true\n"
        + "  },\n"
        + "  {\n"
        + "    \"name\": \"Arkansas\",\n"
        + "    \"code\": \"AR\",\n"
        + "    \"is_enabled\": true\n"
        + "  },\n"
        + "  {\n"
        + "    \"name\": \"California\",\n"
        + "    \"code\": \"CA\",\n"
        + "    \"is_enabled\": true\n"
        + "  }\n"
        + "]";

    try {

     return  objectMapper.readValue(statesJson, new TypeReference<>() {});

    } catch (final Exception e) {
      Log.error("Error loading states json", e);

      return List.of();
    }
  }
}
