package application.impacto_manager_back.security.service;

import application.impacto_manager_back.exceptions.ArgumentsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	private final AuthenticationManager authManager;
	private final Jwtservice jwtService;
	
	public AuthenticationService(AuthenticationManager authManager, Jwtservice jwtService) {
		this.authManager = authManager;
		this.jwtService = jwtService;
	}
	
	public String authenticate(String identifier, String password) {
		if (StringUtils.isBlank(identifier) || StringUtils.isBlank(password)) {
			throw new ArgumentsException("Identificador (e-mail ou username) e senha são obrigatórios");
		}
		
		Authentication authentication = authManager.authenticate(
			new UsernamePasswordAuthenticationToken(identifier, password)
		);
		return jwtService.generateToken(authentication);
	}
}
