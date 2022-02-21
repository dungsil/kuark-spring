package io.kuark.spring.jwt.auth0;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Verification;
import io.kuark.spring.jwt.JwtControllerAutoConfiguration;
import io.kuark.spring.jwt.JwtProperties;
import io.kuark.spring.jwt.JwtService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConditionalOnClass(JWT.class)
@ConditionalOnBean(Algorithm.class)
@EnableConfigurationProperties(JwtProperties.class)
@AutoConfigureBefore(JwtControllerAutoConfiguration.class)
public class Auth0JwtAutoConfiguration {

    @Bean
    public JWTVerifier jwtVerifier(Algorithm algorithm, JwtProperties prop) {
        Verification builder = JWT.require(algorithm);

        if (prop.getJwtId() != null) {
            builder.withJWTId(prop.getJwtId());
        }

        if (prop.getIssuer() != null) {
            builder.withIssuer(prop.getIssuer());
        }

        if (prop.getAudience() != null) {
            builder.withAudience(prop.getAudience());
        }

        if (prop.getSubject() != null) {
            builder.withSubject(prop.getSubject());
        }

        if (prop.getClaims() != null) {
            for (Map.Entry<String, String> claim : prop.getClaims().entrySet()) {
                builder.withClaim(claim.getKey(), claim.getValue());
            }
        }

        return builder.build();
    }

    @Bean
    public JwtService auth0JwtService(Algorithm algorithm, JwtProperties prop, JWTVerifier verifier) {
        return new Auth0JwtService(algorithm, prop, verifier);
    }

    @Configuration
    @AutoConfigureBefore(Auth0JwtAutoConfiguration.class)
    @ConditionalOnProperty(prefix = "kuark.jwt.auth0", name = "algorithm", havingValue = "hs256")
    @EnableConfigurationProperties(Auth0JwtProperties.class)
    static class HS256 {
        @Bean
        public Algorithm hs256(Auth0JwtProperties prop) {
            return Algorithm.HMAC256(prop.getSecret());
        }
    }

    @Configuration
    @AutoConfigureBefore(Auth0JwtAutoConfiguration.class)
    @ConditionalOnProperty(prefix = "kuark.jwt.auth0", name = "algorithm", havingValue = "hs384")
    @EnableConfigurationProperties(Auth0JwtProperties.class)
    static class HS384 {
        @Bean
        public Algorithm hs384(Auth0JwtProperties prop) {
            return Algorithm.HMAC384(prop.getSecret());
        }
    }

    @Configuration
    @AutoConfigureBefore(Auth0JwtAutoConfiguration.class)
    @ConditionalOnProperty(prefix = "kuark.jwt.auth0", name = "algorithm", havingValue = "hs512")
    @EnableConfigurationProperties(Auth0JwtProperties.class)
    static class HS512 {
        @Bean
        public Algorithm hs512(Auth0JwtProperties prop) {
            return Algorithm.HMAC512(prop.getSecret());
        }
    }
}
