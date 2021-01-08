package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class TraineeAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TraineeAlreadyExistsException() {
	}

	public TraineeAlreadyExistsException(String message) {
		super(message);
	}

	public TraineeAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public TraineeAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
