package io.kuark.spring.jwt.auth0;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kuark.jwt.auth0")
public class Auth0JwtProperties {
    private String algorithm;
    private String secret;

    public Auth0JwtProperties() {
        this.algorithm = null;
        this.secret = null;
    }

    public Auth0JwtProperties(String algorithm, String secret) {
        this.algorithm = algorithm;
        this.secret = secret;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getSecret() {
        return secret;
    }
}
