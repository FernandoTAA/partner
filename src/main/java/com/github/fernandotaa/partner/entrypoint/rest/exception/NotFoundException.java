package com.github.fernandotaa.partner.entrypoint.rest.exception;

/**
 * Rest exception represent http status code 404
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
