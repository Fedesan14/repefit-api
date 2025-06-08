package com.fedesan14.repefit_api.utils;

import java.util.Base64;

public class BasicAuthUtils {
    private static String BASIC_PREFIX = "Basic ";
    private static String COLON_SYMBOL = ":";

    private BasicAuthUtils() {}

    public static String extractUsername(String authorization) {
        return getCredentials(authorization)[0];
    }

    private static String[] getCredentials(String authorization) {
        return decodeAuthorizationHeader(authorization).split(COLON_SYMBOL);
    }

    private static String decodeAuthorizationHeader(String authorization) {
        return new String(
                decodeString(
                        authorization
                                .substring(BASIC_PREFIX.length())
                )
        );
    }

    private static byte[] decodeString(String encodedCredentials) {
        return Base64.getDecoder().decode(encodedCredentials);
    }
}
