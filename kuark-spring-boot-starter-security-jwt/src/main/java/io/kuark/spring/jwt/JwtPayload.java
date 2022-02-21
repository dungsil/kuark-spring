package io.kuark.spring.jwt;

public class JwtPayload {
    private String accessToken;

    public JwtPayload(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "JwtPayload{" +
                "accessToken='" + accessToken + '\'' +
            '}';
    }
}
