package application.impacto_manager_back.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class Jwtservice {
    private final JwtEncoder encoder;

    public Jwtservice(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = now.getEpochSecond() + 3600;
        String scopes = authentication
            .getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("spring.application.name")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiry))
            .subject(authentication.getName())
            .claim("scope", scopes)
            .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
