package predictivo.service.exception;

public class InvalidLoginException extends Exception {
	private static final long serialVersionUID = 3339249613410025859L;

	public InvalidLoginException(String message) {
		super(message);
	}
}
