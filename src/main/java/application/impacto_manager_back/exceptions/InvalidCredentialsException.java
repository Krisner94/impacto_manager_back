package application.impacto_manager_back.exceptions;


import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends CustomException {
	public InvalidCredentialsException(String message) {
		super("Credenciais inválidas", message, HttpStatus.UNAUTHORIZED);
	}
}