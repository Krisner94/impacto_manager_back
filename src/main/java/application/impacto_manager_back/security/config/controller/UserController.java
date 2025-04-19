package application.impacto_manager_back.security.config.controller;

import application.impacto_manager_back.security.model.User;
import application.impacto_manager_back.security.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return service.newUser(user.getUsername(), user.getPassword(), user.getRole());
	}
}
