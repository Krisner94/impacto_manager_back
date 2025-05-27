package application.impacto_manager_back.exceptions;

import org.springframework.http.HttpStatus;

public class ArgumentsException extends RuntimeException {
	public ArgumentsException(String message) {
		super(Error.builder()
			.title("Arguments Exception")
			.message(message)
			.httpStatus(HttpStatus.BAD_REQUEST)
			.build()
			.toString());
	}
}
