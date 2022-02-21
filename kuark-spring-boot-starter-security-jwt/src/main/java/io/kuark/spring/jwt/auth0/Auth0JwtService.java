package io.kuark.spring.jwt.auth0;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.kuark.spring.jwt.JwtPayload;
import io.kuark.spring.jwt.JwtProperties;
import io.kuark.spring.jwt.JwtService;

import java.util.Date;
import java.util.Map;

public class Auth0JwtService implements JwtService {
    private final Algorithm algorithm;
    private final JwtProperties prop;
    private final JWTVerifier verifier;

    public Auth0JwtService(Algorithm algorithm, JwtProperties prop, JWTVerifier verifier) {
        this.algorithm = algorithm;
        this.prop = prop;
        this.verifier = verifier;
    }

    @Override
    public JwtPayload createToken(String string) {
        JWTCreator.Builder builder = JWT.create();

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

        Date now = new Date();
        builder.withIssuedAt(now);
        builder.withNotBefore(now);

        if (prop.getClaims() != null) {
            for (Map.Entry<String, String> claim : prop.getClaims().entrySet()) {
                builder.withClaim(claim.getKey(), claim.getValue());
            }
        }

        String accessToken = builder.sign(algorithm);
        return new JwtPayload(accessToken);
    }

    @Override
    public Boolean validateToken(String token) {
        return verifier.verify(token) != null;
    }
}
