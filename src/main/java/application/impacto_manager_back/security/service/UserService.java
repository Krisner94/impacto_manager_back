package application.impacto_manager_back.security.service;

import application.impacto_manager_back.exceptions.CustomException;
import application.impacto_manager_back.exceptions.ObjectNotFoundException;
import application.impacto_manager_back.security.model.User;
import application.impacto_manager_back.security.model.UserDTO;
import application.impacto_manager_back.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	public User findById(Long id){
		return userRepository.findById(id).orElseThrow(
			() -> new ObjectNotFoundException(String.format("Usuário de id %d não encontrado", id))
		);
	}
	
	public User findByEmail(String email){
		return userRepository.findByEmail(email).orElseThrow(
			() -> new ObjectNotFoundException(String.format("Usuário de email %s não encontrado", email))
		);
	}
	
	public User findByUsername(String username){
		return userRepository.findByUsername(username).orElseThrow(
			() -> new ObjectNotFoundException(String.format("Usuário %s não encontrado", username))
		);
	}
	
	public User updateUser(Long id, User user){
		User updateUser = findById(id);
		updateUser.setId(user.getId());
		return userRepository.save(updateUser);
	}
	
	public void deleteUser(Long id){
		userRepository.deleteById(id);
	}
	
	public User newUser(User user) {
		if (StringUtils.isEmpty(user.getRole())){
			throw new CustomException("Role de usuário nao pode ser nula ou vazia");
		}
		
		UserDTO build = UserDTO.builder()
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
