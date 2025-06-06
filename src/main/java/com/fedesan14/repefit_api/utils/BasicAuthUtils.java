package com.fedesan14.repefit_api.utils;

import java.util.Base64;

import static com.fedesan14.repefit_api.utils.Constants.BASIC_PREFIX;
import static com.fedesan14.repefit_api.utils.Constants.COLON_SYMBOL;

public class BasicAuthUtils {
    private BasicAuthUtils() {}


    public static String extractUsername(String authorization) {
        return getCredentials(authorization)[0];
    }

    public static String extractPassword(String authorization) {
        return "";
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
