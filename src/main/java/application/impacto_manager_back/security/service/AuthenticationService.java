package application.impacto_manager_back.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final Jwtservice service;

    public AuthenticationService(Jwtservice service) {
        this.service = service;
    }


    public String authenticate(Authentication authentication) {
        return service.generateToken(authentication);
    }
}
