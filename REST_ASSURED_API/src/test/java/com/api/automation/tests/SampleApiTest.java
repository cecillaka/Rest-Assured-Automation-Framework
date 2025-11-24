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

import static com.api.automation.utils.JsonUtils.readJsonFile;

public class SampleApiTest extends BaseTest {
public String generatedUserPayLoad;


    @Test(description = "Register A Users", priority = 0)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Create A User")
    public void postRegisterAUsersTest() {

        String payload = PayloadGenerator.createRandomUserPayload();
        Response response = ApiRequestBuilder.post("/api/auth/signup",payload);
        ApiResponseValidator.validateStatusCode(response, 200);
        ApiResponseValidator.validateResponseBody(response,"User registered successfully!");
        generatedUserPayLoad=payload;

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

    }


    @Test(description = "Create Account",priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Account")
    public void postCreateAccount() {

        String payload = JsonUtils.readJsonFile("src/test/resources/payloads/CreateAccount.json");
        Response response = ApiRequestBuilder.post("api/accounts",payload);
        ApiResponseValidator.validateStatusCode(response, 200);

        //store values globally
        //SessionContext.setAccountNumber(response.jsonPath().getString("accountNumber"));

    }

    @Test(description = "Get Created Accounts",priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Account")
    public void getAccounts() {

        Response response = ApiRequestBuilder.get("api/accounts/user");
        ApiResponseValidator.validateStatusCode(response, 200);

    }


}
