package io.kuark.spring.jwt;

import io.kuark.spring.jwt.auth0.Auth0JwtAutoConfiguration;
import io.kuark.spring.jwt.auth0.Auth0JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@SpringBootConfiguration
@Import(Auth0JwtAutoConfiguration.class)
class KuarkSpringBootStarterSecurityJwtApplicationTests {

    @Autowired
    private Auth0JwtService auth0;

    @Test
    void contextLoads() {}

    @Test
    void auth0() {
        JwtPayload payload = auth0.createToken("Token!");

        Assertions.assertTrue(auth0.validateToken(payload));
    }
}
