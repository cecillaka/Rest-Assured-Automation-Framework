package com.api.automation.core;

import com.api.automation.utils.ConfigReader;
import io.qameta.allure.Allure;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequestBuilder {

    private static final String BASE_URL = ConfigReader.get("base.url");
    private static final int TIMEOUT = Integer.parseInt(ConfigReader.get("timeout")) * 1000;

    private static final RestAssuredConfig REQUEST_CONFIG = RestAssuredConfig.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", TIMEOUT)
                    .setParam("http.socket.timeout", TIMEOUT)
                    .setParam("http.connection-manager.timeout", TIMEOUT)
                    .setParam("http.protocol.expect-continue", false)

            );

    /**
     * Returns the base request spec shared by all requests
     */
    private static RequestSpecification baseRequest() {
        RequestSpecification req = given()
                .config(REQUEST_CONFIG)
                //.baseUri(BASE_URL)
                .header("Content-Type", "application/json");

        // Attach token if present
        String token = SessionContext.getToken();
        if (token != null) {
            req.header("Authorization", "Bearer " + token);
        }

        return req;
    }

    private static void attachResponse(String endpoint, Response response) {
        Allure.addAttachment(
                endpoint + " Response",
                "application/json",
                response.asPrettyString(),
                ".json"
        );
    }

    public static Response get(String endpoint) {
        Response response = baseRequest()
                .when()
                .get(endpoint);

        attachResponse(endpoint, response);
        return response;
    }

    public static Response get(String endpoint, Map<String, Object> pathParams) {
        Response response = baseRequest()
                .pathParams(pathParams)
                .when()
                .get(endpoint);

        attachResponse(endpoint, response);
        return response;
    }

    public static Response post(String endpoint, String body) {
        Response response = baseRequest()
                .body(body)
                .when()
                .post(endpoint);

        attachResponse(endpoint, response);
        return response;
    }

    public static Response put(String endpoint, String body) {
        Response response = baseRequest()
                .body(body)
                .when()
                .put(endpoint);

        attachResponse(endpoint, response);
        return response;
    }

    public static Response put(String endpoint,String body,Map<String, Object> pathParams) {
        Response response = baseRequest()
                .pathParams(pathParams)
                .body(body)
                .when()
                .put(endpoint);

        attachResponse(endpoint, response);
        return response;
    }


    public static Response delete(String endpoint) {
        Response response = baseRequest()
                .when()
                .delete(endpoint);

        attachResponse(endpoint, response);
        return response;
    }

    public static Response delete(String endpoint, Map<String, Object> pathParams) {
        Response response = baseRequest()
                .pathParams(pathParams)
                .when()
                .delete(endpoint);

        attachResponse(endpoint, response);
        return response;
    }
}
