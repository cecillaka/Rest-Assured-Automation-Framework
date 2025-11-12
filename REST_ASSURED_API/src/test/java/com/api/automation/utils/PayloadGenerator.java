package com.api.automation.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PayloadGenerator {

    public static String createRandomUserPayload() {
        try {
            Random random = new Random();
            int randomNumber = 1000 + random.nextInt(9000);

            Map<String, Object> user = new HashMap<>();
            user.put("username", "TempUser" + randomNumber);
            user.put("password", "P@$$w0rd@1996");
            user.put("email", "TempUser" + randomNumber + "@gmail.com");
            user.put("firstName", "TempUser" + randomNumber);
            user.put("lastName", "TempULastName" + randomNumber);
            user.put("mobileNumber", "065834" + (1000 + random.nextInt(8999)));

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(user);

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate user payload", e);
        }
    }
}
