package com.api.automation.tests;

import com.api.automation.base.BaseTest;
import com.api.automation.core.ApiRequestBuilder;
import com.api.automation.core.ApiResponseValidator;
import com.api.automation.core.SessionContext;
import com.api.automation.utils.JsonUtils;
import com.api.automation.utils.PayloadGenerator;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class SampleApiTest extends BaseTest {
public String generatedUserPayLoad;


    @Test(description = "Register A Users", priority = 0)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Create A User")
    public void getUsersTest() {

        String payload = PayloadGenerator.createRandomUserPayload();
        Response response = ApiRequestBuilder.post("/api/auth/signup",payload);
        ApiResponseValidator.validateStatusCode(response, 200);
        ApiResponseValidator.validateResponseBody(response,"User registered successfully!");
        generatedUserPayLoad=payload;
        Allure.addAttachment("Full Response", "application/json", response.asPrettyString(), ".json");
    }
    @Test(description = "Log In As A Created Users",priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login As A User")
    public void postLogInAsUserCreated() {

        String payload =generatedUserPayLoad;
        Response response = ApiRequestBuilder.post("/api/auth/login",payload);
        ApiResponseValidator.validateStatusCode(response, 200);
        // Extract values
        String token = response.jsonPath().getString("token");
        int userId = response.jsonPath().getInt("id");

        // Store globally
        SessionContext.setToken(token);
        SessionContext.setUserId(userId);
        Allure.addAttachment("Full Response", "application/json", response.asPrettyString(), ".json");
    }

}
