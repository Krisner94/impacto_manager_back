package application.impacto_manager_back.exceptions;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends RuntimeException {
	public ObjectNotFoundException(String message, HttpStatus httpStatus) {
		super(Error.builder()
			.title(message)
			.message("Erro interno")
			.httpStatus(httpStatus)
			.build()
			.toString());
	}
	
	public ObjectNotFoundException(String message) {
		super(Error.builder()
			.title(message)
			.message("Erro interno")
			.httpStatus(HttpStatus.BAD_REQUEST)
			.build()
			.toString());
	}
}