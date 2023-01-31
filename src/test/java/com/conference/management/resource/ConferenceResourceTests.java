package com.conference.management.resource;

import com.conference.management.resource.constants.TestConstants;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConferenceResourceTests {

    @LocalServerPort
    private int port;

    @Order(1)
    @Test
    public void createConferenceTest() {

        given().port(port).accept(ContentType.JSON)
                .contentType(ContentType.JSON).body(new File("src/test/resources/post-conference-body.json"))
                .post(TestConstants.POST_CONFERENCE_URL).then().statusCode(200)
                .body("conferenceId", notNullValue());

    }

    @Sql(value = "classpath:init-conf.sql", executionPhase = BEFORE_TEST_METHOD)
    @Order(2)
    @Test
    public void getConferenceAvailability() {
        given().port(port)
                .get(String.format(TestConstants.PARAM_CONFERENCE_URL, 2)).then()
                .statusCode(200).body("available", is(true))
                .body("seatsAvailable", equalTo(200));
    }


    @Sql(value = "classpath:init-conf.sql", executionPhase = BEFORE_TEST_METHOD)
    @Test
    @Order(3)
    public void deleteConference() {
        given().port(port)
                .delete(String.format(TestConstants.PARAM_CONFERENCE_URL, 1)).then()
                .statusCode(200);
    }
}
