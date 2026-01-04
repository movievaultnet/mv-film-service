package io.github.cciglesiasmartinez.microservice_template.domain.exception;

public class DuplicatedFilmTitleException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MESSAGE = "Wrong film title.";
	
	public DuplicatedFilmTitleException() {
		super(DEFAULT_MESSAGE);
	}
	
	public DuplicatedFilmTitleException(String message) {
        super(message);
    }

}
