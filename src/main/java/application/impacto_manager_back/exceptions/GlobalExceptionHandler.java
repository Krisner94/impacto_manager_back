package application.impacto_manager_back.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Error> handleCustomException(CustomException ex) {
		return ResponseEntity.status(ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.BAD_REQUEST).body(
			Error.builder()
				.title(ex.getMessage() != null ? ex.getMessage() : "Erro interno")
				.message(ex.getTitle() != null ? ex.getTitle() : "Erro")
				.httpStatus(ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.BAD_REQUEST)
				.build()
		);
	}
}