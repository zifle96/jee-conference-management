package com.conference.management.resource;

import com.conference.management.resource.constants.TestConstants;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParticipantResourceTests {

    @LocalServerPort
    private int port;


    @Test
    public void createParticipantTest() {

        given().port(port).accept(ContentType.JSON)
                .contentType(ContentType.JSON).body(new File("src/test/resources/post-participant-body.json"))
                .post(String.format(TestConstants.POST_PARTICIPANT_URL, 1)).then().statusCode(200)
                .body("participantId", notNullValue())
                .body("participantId", is(1));

    }

    @Sql(value = "classpath:init-part.sql", executionPhase = BEFORE_TEST_METHOD)
    @Test
    public void removeParticipantTest200() {
        given().port(port)
                .delete(String.format(TestConstants.DELETE_PARTICIPANT_FROM_CONF, 1, 1)).then()
                .statusCode(200);
    }

    @Test
    public void removeParticipantTest500() {
        given().port(port)
                .delete(String.format(TestConstants.DELETE_PARTICIPANT_FROM_CONF, 1, 2))
                .then()
                .statusCode(500).body("message", equalTo("Conference with id 2 doesnt exist"));
    }
}
