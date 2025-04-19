package application.impacto_manager_back.security.service;

import application.impacto_manager_back.security.model.User;
import application.impacto_manager_back.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User newUser(String username, String password, String role) {
		User User = new User();
		User.setUsername(username);
		User.setPassword(passwordEncoder.encode(password));
		User.setRole(role);
		return userRepository.save(User);
	}
}
