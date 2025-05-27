package application.impacto_manager_back.exceptions;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends CustomException {
	public ObjectNotFoundException(String message, HttpStatus httpStatus) {
		super(Error.builder()
			.title(message)
			.message("Erro interno")
			.httpStatus(HttpStatus.BAD_REQUEST)
			.build());
		
		
	}
	
	public ObjectNotFoundException(String message) {
		super(Error.builder()
			.title(message)
			.message("Erro interno")
			.httpStatus(HttpStatus.BAD_REQUEST)
			.build());
		
		
	}
}