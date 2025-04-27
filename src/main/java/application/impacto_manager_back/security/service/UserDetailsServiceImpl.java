package application.impacto_manager_back.security.service;

import application.impacto_manager_back.exceptions.Error;
import application.impacto_manager_back.exceptions.UserNotFoundException;
import application.impacto_manager_back.security.config.UserAuthenticated;
import application.impacto_manager_back.security.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		return userRepository.findByUsername(usernameOrEmail)
			.map(UserAuthenticated::new)
			.or(() -> userRepository.findByEmail(usernameOrEmail).map(UserAuthenticated::new))
			.orElseThrow(() -> new UserNotFoundException(
				Error.builder()
					.title("Generic error exception")
					.message("User not found")
					.httpStatus(HttpStatus.BAD_REQUEST)
					.build()
			));
	}
}
