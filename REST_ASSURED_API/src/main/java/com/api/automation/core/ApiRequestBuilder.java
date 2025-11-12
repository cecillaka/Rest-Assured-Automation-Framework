package com.api.automation.core;

import com.api.automation.utils.ConfigReader;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiRequestBuilder {

    // Read timeout from your ConfigReader (in seconds) and convert to milliseconds
    private static final int TIMEOUT = Integer.parseInt(ConfigReader.get("timeout")) * 1000;


    // Create a RestAssuredConfig with timeout
    private static final RestAssuredConfig REQUEST_CONFIG = RestAssuredConfig.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", TIMEOUT)
                    .setParam("http.socket.timeout", TIMEOUT)
            );

    public static Response get(String endpoint) {
        return given()
                .config(REQUEST_CONFIG)
                .when()
                .get(endpoint);
    }

    public static Response post(String endpoint, String body) {
        return given()
                .config(REQUEST_CONFIG)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint);

    }

    public static Response put(String endpoint, String body) {
        return given()
                .config(REQUEST_CONFIG)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return given()
                .config(REQUEST_CONFIG)
                .when()
                .delete(endpoint);
    }
}
