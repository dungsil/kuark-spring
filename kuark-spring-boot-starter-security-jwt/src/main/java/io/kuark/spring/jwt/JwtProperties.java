package io.kuark.spring.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@ConfigurationProperties(prefix = "kuark.jwt")
public class JwtProperties {
    private final String subject;
    private final String jwtId;
    private final String issuer;
    private final String[] audience;
    private final Map<String, String> claims;

    private final int expireAt;
    private final TimeUnit timeUnit;

    public JwtProperties() {
        this.subject = null;
        this.jwtId = null;
        this.issuer = null;
        this.audience = null;
        this.claims = null;

        this.expireAt = 30;
        this.timeUnit = TimeUnit.MINUTES;
    }

    public JwtProperties(String subject, String jwtId, String issuer, String audience, Map<String, String> claims, int expireAt, TimeUnit timeUnit) {
        this.subject = subject;
        this.jwtId = jwtId;
        this.issuer = issuer;
        this.audience = new String[]{audience};
        this.claims = claims;
        this.expireAt = expireAt;
        this.timeUnit = timeUnit;
    }

    public JwtProperties(String subject, String jwtId, String issuer, String[] audience, Map<String, String> claims, int expireAt, TimeUnit timeUnit) {
        this.subject = subject;
        this.jwtId = jwtId;
        this.issuer = issuer;
        this.audience = audience;
        this.claims = claims;
        this.expireAt = expireAt;
        this.timeUnit = timeUnit;
    }

    public String getSubject() {
        return subject;
    }

    public String getJwtId() {
        return jwtId;
    }

    public String getIssuer() {
        return issuer;
    }

    public String[] getAudience() {
        return audience;
    }

    public Date getExpireAt(Date now) {
        long expireMilli = this.timeUnit.toMillis(this.expireAt);

        return new Date(now.getTime() + expireMilli);
    }

    public Map<String, String> getClaims() {
        return claims;
    }
}

