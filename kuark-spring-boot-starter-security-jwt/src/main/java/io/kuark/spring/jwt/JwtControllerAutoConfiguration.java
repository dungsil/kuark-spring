package io.kuark.spring.jwt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(JwtService.class)
public class JwtControllerAutoConfiguration {

    @Bean
    public JwtController jwtController(JwtService service) {
        return new JwtController(service);
    }
}
