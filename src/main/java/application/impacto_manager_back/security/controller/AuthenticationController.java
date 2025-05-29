package application.impacto_manager_back.security.controller;

import application.impacto_manager_back.security.model.UserLogin;
import application.impacto_manager_back.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    
    @PostMapping("/login")
    public String login(@RequestBody UserLogin userLogin) {
        String identifier = StringUtils.isNotBlank(userLogin.getEmail())
            ? userLogin.getEmail()
            : userLogin.getUsername();
        
        return service.authenticate(identifier, userLogin.getPassword());
    }
}
