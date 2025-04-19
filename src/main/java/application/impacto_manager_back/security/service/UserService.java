package application.impacto_manager_back.security.service;

import application.impacto_manager_back.security.model.User;
import application.impacto_manager_back.security.model.UserDto;
import application.impacto_manager_back.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User newUser(User user) {
		UserDto build = UserDto.builder()
		  .username(user.getUsername())
		  .password(passwordEncoder.encode(user.getPassword()))
		  .role(user.getRole())
		  .phone(user.getPhone())
		  .email(user.getEmail())
		  .build();
		
		copyProperties(build, user);
		
		return userRepository.save(user);
	}
}
