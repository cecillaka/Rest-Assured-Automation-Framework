package com.api.automation.core;


public class SessionContext {

    private static String token;
    private static int userId;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SessionContext.token = token;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        SessionContext.userId = userId;
    }

    public static void clear() {
        token = null;
        userId = 0;
    }
}
