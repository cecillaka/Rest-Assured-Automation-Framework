package com.api.automation.base;

import com.api.automation.core.BaseApi;
import com.api.automation.core.SessionContext;
import com.api.automation.listeners.TestListener;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class BaseTest {

    @BeforeClass
    public void setup() {
        BaseApi.setBaseUri();  // set RestAssured base URI
        // Add Allure filter and console logging filters
        RestAssured.filters(
                new RequestLoggingFilter(),       // Logs requests to console
                new ResponseLoggingFilter(),      // Logs responses to console
                new AllureRestAssured()           // Automatically sends logs to Allure report

        );

    }

    @AfterSuite
    public void tearDown() {
        SessionContext.clear();
    }
}
