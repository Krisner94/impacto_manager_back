package application.impacto_manager_back.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {
	public UserNotFoundException(String message) {
		super("Usuário não encontrado", message, HttpStatus.NOT_FOUND);
	}
}