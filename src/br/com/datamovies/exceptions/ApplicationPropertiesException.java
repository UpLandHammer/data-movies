package br.com.datamovies.exceptions;

public class ApplicationPropertiesException extends RuntimeException {
    public ApplicationPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationPropertiesException(String message) {
        super(message);
    }
}
