package com.fedesan14.repefit_api.services.interfaces;

public interface JsonWebTokenService {
    String getRefreshToken(String subject);
    String getAccessToken(String subject);
}
