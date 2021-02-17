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
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ApiError> notFound(RuntimeException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        String errorMessage = MessageFormat.format("{0} parameter is missing", exception.getParameterName());
        var error = new Error("path", exception.getParameterName(), null, errorMessage);
        var apiError = new ApiError("some fields contain errors", List.of(error));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<ApiError> constraintViolationException(ConstraintViolationException exception) {
        var errors = exception.getConstraintViolations().stream()
                .map(error -> new Error("path", error.getPropertyPath().toString(), error.getInvalidValue().toString(), error.getMessage()))
                .collect(Collectors.toList());
        ApiError apiError = new ApiError("some fields contain errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        var attributeErrors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new Error("attribute", error.getField(), String.valueOf(error.getRejectedValue()), error.getDefaultMessage()))
                .collect(Collectors.toList());

        var validationErrors = exception.getBindingResult().getGlobalErrors()
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
