package application.impacto_manager_back.security.config.controller;

import application.impacto_manager_back.security.service.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("login")
    public String  authenticate(Authentication authentication) {
        return service.authenticate(authentication);
    }
}
