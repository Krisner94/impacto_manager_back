package application.impacto_manager_back.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
public class CustomException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = -7034897190745766940L;
	private String title;
	private String message;
	private HttpStatus httpStatus;
	private Error error;
	
	public CustomException(Error error) {
		this.title = error.getTitle();
		this.message = error.getMessage();
		this.httpStatus = error.getHttpStatus();
	}
	
	public CustomException(String message) {
		this.message = message;
	}
}