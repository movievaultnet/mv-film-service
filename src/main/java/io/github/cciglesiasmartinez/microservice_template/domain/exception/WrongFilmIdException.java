package io.github.cciglesiasmartinez.microservice_template.domain.exception;

public class WrongFilmIdException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MESSAGE = "Wrong ItemId.";
	
	public WrongFilmIdException() {
		super(DEFAULT_MESSAGE);
	}
	
	public WrongFilmIdException(String message) {
		super(message);
	}
	
	
}
