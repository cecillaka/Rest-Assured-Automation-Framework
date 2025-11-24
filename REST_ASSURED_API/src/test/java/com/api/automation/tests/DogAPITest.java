package com.api.automation.tests;

import com.api.automation.base.BaseTest;
import com.api.automation.core.ApiRequestBuilder;
import com.api.automation.core.ApiResponseValidator;
import com.api.automation.core.SessionContext;
import com.api.automation.utils.JsonUtils;
import com.api.automation.utils.PayloadGenerator;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DogAPITest extends BaseTest {
public String generatedUserPayLoad;


    @Test(description = "Get Breeds",priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Breeds")
    public void getADogBreads() {

        Response response = ApiRequestBuilder.get("/breeds");
        ApiResponseValidator.validateStatusCode(response, 200);

    }


}
