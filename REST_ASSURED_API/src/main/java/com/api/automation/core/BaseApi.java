package com.api.automation.core;
import com.api.automation.utils.ConfigReader;
import io.restassured.RestAssured;

public class BaseApi {

    public static String getBaseUrl() {
        return ConfigReader.get("base.url");
    }

    public static void setBaseUri() {
        RestAssured.baseURI = getBaseUrl();
    }
}
