package application.impacto_manager_back.security.model;

import lombok.Data;

@Data
public class UserLogin {
	private String email;
	private String username;
	private String password;
}
