package com.api.automation.core;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiRequestBuilder {

    public static Response get(String endpoint) {
        return given()
                .when()
                .get(endpoint);
    }

    public static Response post(String endpoint, String body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response put(String endpoint, String body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return given()
                .when()
                .delete(endpoint);
    }
}
