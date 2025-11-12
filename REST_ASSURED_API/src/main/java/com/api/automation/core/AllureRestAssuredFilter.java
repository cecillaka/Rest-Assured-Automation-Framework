package com.api.automation.core;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.nio.charset.StandardCharsets;

public class AllureRestAssuredFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        // Capture Request Info
        String requestInfo = "REQUEST:\n"
                + requestSpec.getMethod() + " " + requestSpec.getURI() + "\n\n"
                + "Headers:\n" + requestSpec.getHeaders() + "\n\n"
                + "Body:\n" + (requestSpec.getBody() != null ? requestSpec.getBody() : "No Body");

        // Add Request Log as plain text
        Allure.addAttachment("API Request", "text/plain",
                requestInfo, StandardCharsets.UTF_8.name());

        // Execute the request
        Response response = ctx.next(requestSpec, responseSpec);

        // Capture Response Info
        String responseInfo = "RESPONSE:\n"
                + "Status Code: " + response.getStatusCode() + "\n\n"
                + "Headers:\n" + response.getHeaders() + "\n\n"
                + "Body:\n" + response.getBody().asPrettyString();

        // Add Response Log as plain text
        Allure.addAttachment("API Response", "text/plain",
                responseInfo, StandardCharsets.UTF_8.name());

        return response;
    }
}
