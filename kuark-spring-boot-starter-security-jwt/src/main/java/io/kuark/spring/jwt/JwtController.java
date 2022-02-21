package io.kuark.spring.jwt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/token")
public class JwtController {
    private final JwtService srv;

    public JwtController(JwtService srv) {
        this.srv = srv;
    }

    @PostMapping
    public JwtPayload createToken() {
        return srv.createToken("Token");
    }
}
