package com.api.automation.core;


public class SessionContext {

    private static String token;
    private static int userId;
    private static String accountNumber;

    public static String getToken() {
        return token;
    }

    public static String getAccountNumber() {
        return accountNumber;
    }

    public static void setToken(String token) {
        SessionContext.token = token;
    }
    public static void setAccountNumber(String accountNumber) {
        SessionContext.accountNumber = accountNumber;
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
        accountNumber = null;
    }

}
