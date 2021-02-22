package com.github.fernandotaa.partner.entrypoint.rest.exception;

/**
 * REST exception represent HTTP status code 404
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
