package com.api.automation.core;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiResponseValidator {

    public static void validateStatusCode(Response response, int expected) {
        assertThat("Status code mismatch!", response.getStatusCode(), equalTo(expected));
    }

    public static void validateFieldNotEmpty(Response response, String jsonPath) {
        assertThat("Field is null or empty!", response.jsonPath().get(jsonPath), notNullValue());
    }

    public static void validateResponseBody(Response response, String expectedText) {
        String body = response.getBody().asString();
        assertThat("Response body is null", body, notNullValue());
        assertThat("Response body does not contain expected text", body, containsString(expectedText));
    }
}
