package io.github.cciglesiasmartinez.microservice_template.domain.exception;

public class EditionNotFoundException extends RuntimeException {
    public EditionNotFoundException(String message) {
        super(message);
    }
}
