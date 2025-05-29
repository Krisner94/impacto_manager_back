package application.impacto_manager_back.security.controller;

import application.impacto_manager_back.exceptions.ObjectNotFoundException;
import application.impacto_manager_back.security.model.User;
import application.impacto_manager_back.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService service;
	
	@GetMapping("")
	public ResponseEntity<List<User>> findAll() {
		try {
			List<User> users = service.findAllUsers();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao buscar usuários", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		try {
			return service.findById(id);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao buscar usuário", HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/find/{value}")
	public ResponseEntity<User> findByUsername(@PathVariable String value) {
		try {
			User users = service.findByUsername(value);
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao buscar usuário", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/find/username/{value}")
	public ResponseEntity<User> findByEmail(@PathVariable String value) {
		try {
			User users = service.findByEmail(value);
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro ao buscar usuário", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user, Authentication authentication) {
		boolean hasUsers = !service.findAllUsers().isEmpty();
		
		if (hasUsers) {
			if (authentication == null || !hasRequiredRole(authentication, List.of("ROLE_ADMIN", "ROLE_ROOT"))) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
			}
			if (user.getRole() == null || user.getRole().isBlank()) {
				return ResponseEntity.badRequest().body("Role deve ser informada para novos usuários");
			}
		} else {
			user.setRole("ROOT");
		}
		
		User savedUser = service.newUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@PutMapping
	public ResponseEntity<User> update(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(service.updateUser(user.getId(), user));
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestHeader("id") Long id) {
		service.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	private boolean hasRequiredRole(Authentication auth, List<String> roles) {
		return auth.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.anyMatch(roles::contains);
	}
}

