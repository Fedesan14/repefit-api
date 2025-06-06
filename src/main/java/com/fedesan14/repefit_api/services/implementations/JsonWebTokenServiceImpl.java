package com.fedesan14.repefit_api.services.implementations;

import com.fedesan14.repefit_api.services.interfaces.JsonWebTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JsonWebTokenServiceImpl implements JsonWebTokenService {

    @Value("${security.access-token-expiration}")
    private long accessTokenExpiration;
    @Value("${security.refresh-token-expiration}")
    private long refreshTokenExpiration;
    @Value("${security.key}")
    private String securityKey;

    @Override
    public String getAccessToken(String subject) {
        return buildJwt(
                subject,
                Instant.now().plus(accessTokenExpiration, ChronoUnit.MINUTES)
        );
    }

    @Override
    public String getRefreshToken(String subject) {
        return buildJwt(
                subject,
                Instant.now().plus(refreshTokenExpiration, ChronoUnit.MINUTES)
        );
    }

    private String buildJwt(String subject, Instant expiration) {
        return Jwts
                .builder()
                .subject(subject)
                .signWith(getSecretKey())
                .expiration(Date.from(expiration))
                .issuedAt(new Date())
                .compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(securityKey)
        );
    }
}
