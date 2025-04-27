package application.impacto_manager_back.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Error> handleCustomException(CustomException ex) {
		return ResponseEntity.status(ex.getHttpStatus()).body(
			Error.builder()
				.title(ex.getMessage())
				.message(ex.getMessage())
				.httpStatus(ex.getHttpStatus())
				.build()
		);
	}
}