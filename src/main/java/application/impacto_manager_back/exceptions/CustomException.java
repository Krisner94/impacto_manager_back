package application.impacto_manager_back.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException {
	private final String title;
	private final String message;
	private final HttpStatus httpStatus;
	
	public CustomException(String title, String message, HttpStatus httpStatus) {
		this.title = title;
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	public CustomException(Error error) {
		this.title = error.getTitle();
		this.httpStatus = error.getHttpStatus();
		this.message = error.getMessage();
	}
}