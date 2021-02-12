package com.github.fernandotaa.partner.entrypoint.rest.handler;

import com.github.fernandotaa.partner.entrypoint.rest.exception.NotFoundException;
import com.github.fernandotaa.partner.entrypoint.rest.handler.data.ApiError;
import com.github.fernandotaa.partner.entrypoint.rest.handler.data.Error;
import org.apache.commons.collections4.ListUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ApiError> notFound(RuntimeException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var attributeErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error ->
                        new Error("attribute", error.getField(), String.valueOf(error.getRejectedValue()), error.getDefaultMessage())
                ).collect(Collectors.toList());

        var validationErrors = ex.getBindingResult().getGlobalErrors()
                .stream()
                .map(error -> new Error("validation", null, null, error.getDefaultMessage()))
                .collect(Collectors.toList());

        var apiError = new ApiError("some fields contain errors", ListUtils.union(attributeErrors, validationErrors));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var apiError = new ApiError(ex.getMostSpecificCause().getMessage(), null);
        return ResponseEntity.status(status).body(apiError);
    }

}
