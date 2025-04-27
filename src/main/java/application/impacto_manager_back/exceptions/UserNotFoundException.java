package application.impacto_manager_back.exceptions;

public class UserNotFoundException extends CustomException {
	public UserNotFoundException(Error error) {
		super(error);
	}
}