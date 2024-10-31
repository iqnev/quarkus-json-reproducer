package org.acme;

import static io.restassured.RestAssured.given;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.OK;

import com.fasterxml.jackson.core.type.TypeReference;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.Test;

@QuarkusTest
class GreetingResourceTest {

  @Inject
  ObjectSerializer objectSerializer;

  @Test
  void testHelloEndpoint() {
    final ValidatableResponse actualResponse = given()
        .when().get("/hello")
        .then();

    actualResponse.statusCode(OK);

    final List<StateInfoDto> actualResultList =
        objectSerializer.toObject(
            actualResponse.extract().asString(), ClassTypeProvider.of(new TypeReference<>() {
            }));

    System.out.printf("actualResultList: %s%n", actualResultList);
  }

}