package io.kuark.spring.jwt;

public interface JwtService {
    JwtPayload createToken(String string);

    Boolean validateToken(String token);

    default Boolean validateToken(JwtPayload payload) {
        return validateToken(payload.getAccessToken());
    }
}
