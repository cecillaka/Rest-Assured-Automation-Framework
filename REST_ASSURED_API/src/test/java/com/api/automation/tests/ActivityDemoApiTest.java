package com.api.automation.tests;
import com.api.automation.base.BaseTest;
import com.api.automation.core.ApiRequestBuilder;
import com.api.automation.core.ApiResponseValidator;
import com.api.automation.utils.JsonUtils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;

public class ActivityDemoApiTest extends BaseTest {

    @Test(description = "Get Activities",priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get Activity")
    public void getActivities() {

        Response response = ApiRequestBuilder.get("api/v1/Activities");
        ApiResponseValidator.validateStatusCode(response, 200);

    }

    @Test(description = "Post Activity", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Create an Activity")
    public void postActivity() {

        String payload = JsonUtils.readJsonFile("src/test/resources/payloads/CreateActivity.json");
        Response response = ApiRequestBuilder.post("/api/v1/Activities",payload);
        ApiResponseValidator.validateStatusCode(response, 200);
        ApiResponseValidator.validateResponseBody(response,"System Planing");
    }

    @Test(description = "Get Activities By ID", priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get Activity")
    public void getActivitiesById() {

        Map<String, Object> params = Map.of("id", 20);

        Response response = ApiRequestBuilder.get("api/v1/Activities/{id}", params);
        ApiResponseValidator.validateStatusCode(response, 200);
        ApiResponseValidator.validateResponseBody(response,"Activity 20");
    }

    @Test(description = "Put Activities By ID", priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    @Story("put Activity")
    public void putActivitiesById() {

        Map<String, Object> params = Map.of("id", 20);
        String payload = JsonUtils.readJsonFile("src/test/resources/payloads/PutActivity.json");

        //replacing the old object with the new json object
        Response response = ApiRequestBuilder.put("api/v1/Activities/{id}",payload, params);
        ApiResponseValidator.validateStatusCode(response, 200);
        ApiResponseValidator.validateResponseBody(response,"Test Case Design");
    }

    @Test(description = "delete Activities By ID", priority = 5)
    @Severity(SeverityLevel.CRITICAL)
    @Story("delete Activity")
    public void deleteActivitiesById() {

        Map<String, Object> params = Map.of("id", 20);

        //replacing the old object with the new json object
        Response response = ApiRequestBuilder.delete("api/v1/Activities/{id}", params);
        ApiResponseValidator.validateStatusCode(response, 200);
    }


}
